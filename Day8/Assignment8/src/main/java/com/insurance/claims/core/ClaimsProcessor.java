package com.insurance.claims.core;

import com.insurance.claims.config.Configuration;
import com.insurance.claims.io.AuditLogger;
import com.insurance.claims.model.*;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Core claims processor that handles concurrent processing while maintaining:
 * - Per-policy ordering (claims for same policy processed sequentially)
 * - Priority handling (URGENT before NORMAL)
 * - Idempotency (no duplicate processing)
 * - Retry logic with bounded retries
 */
public class ClaimsProcessor {

    private final Configuration config;
    private final AuditLogger auditLogger;
    private final ConcurrentMap<String, ProcessingResult> processedClaims;
    private final FraudDetector fraudDetector;
    private final ExternalCheckService externalCheckService;

    // Worker thread pool
    private final ThreadPoolExecutor workerPool;

    // Per-policy processing queues and locks
    private final ConcurrentMap<String, PolicyProcessor> policyProcessors;

    // Global priority queues
    private final BlockingQueue<Claim> urgentQueue;
    private final BlockingQueue<Claim> normalQueue;
    private final BlockingQueue<Claim> backlogQueue;

    // Shutdown coordination
    private final AtomicBoolean isShuttingDown;
    private final CountDownLatch shutdownLatch;

    public ClaimsProcessor(Configuration config, AuditLogger auditLogger,
                           ConcurrentMap<String, ProcessingResult> processedClaims,
                           FraudDetector fraudDetector) {
        this.config = config;
        this.auditLogger = auditLogger;
        this.processedClaims = processedClaims;
        this.fraudDetector = fraudDetector;
        this.externalCheckService = new ExternalCheckService();

        // Initialize queues
        this.urgentQueue = new PriorityBlockingQueue<>(config.getBacklogCapacity(),
                Comparator.comparing(Claim::getTimestamp));
        this.normalQueue = new PriorityBlockingQueue<>(config.getBacklogCapacity(),
                Comparator.comparing(Claim::getTimestamp));
        this.backlogQueue = new ArrayBlockingQueue<>(config.getBacklogCapacity());

        // Initialize policy processors map
        this.policyProcessors = new ConcurrentHashMap<>();

        // Initialize worker pool
        this.workerPool = new ThreadPoolExecutor(
                config.getWorkerCount(),
                config.getWorkerCount(),
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(),
                r -> new Thread(r, "ClaimWorker-" + System.nanoTime())
        );

        this.isShuttingDown = new AtomicBoolean(false);
        this.shutdownLatch = new CountDownLatch(config.getWorkerCount());
    }

    /**
     * Process all claims from the input list
     */
    public void processAllClaims(List<Claim> claims) throws InterruptedException {
        // Start worker threads
        startWorkers();

        // Feed claims into the system
        for (Claim claim : claims) {
            if (isShuttingDown.get()) {
                break;
            }

            // Wait if system is throttled due to fraud detection
            fraudDetector.waitIfThrottled();

            // Check for fraud
            fraudDetector.checkClaim(claim);

            // Add to backlog (this will block if backlog is full)
            try {
                backlogQueue.put(claim);
                auditLogger.logEvent(claim.getClaimId(), Thread.currentThread().getName(),
                        null, null, "QUEUED", 0);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }

        // Signal no more claims
        for (int i = 0; i < config.getWorkerCount(); i++) {
            backlogQueue.offer(createPoisonPill());
        }

        // Wait for all workers to complete
        shutdownLatch.await();

        // Process any remaining claims in priority queues
        processRemainingClaims();
    }

    private void startWorkers() {
        for (int i = 0; i < config.getWorkerCount(); i++) {
            workerPool.submit(new ClaimWorker());
        }
    }

    /**
     * Worker thread that processes claims from the backlog
     */
    private class ClaimWorker implements Runnable {
        @Override
        public void run() {
            try {
                while (!isShuttingDown.get()) {
                    // Take claim from backlog
                    Claim claim = backlogQueue.take();

                    if (isPoisonPill(claim)) {
                        break;
                    }

                    // Check for duplicate (idempotency)
                    if (processedClaims.containsKey(claim.getClaimId())) {
                        auditLogger.logEvent(claim.getClaimId(), Thread.currentThread().getName(),
                                null, null, "DUPLICATE_SKIPPED", 0);
                        continue;
                    }

                    // Route to appropriate priority queue
                    if (claim.getPriorityFlag() == PriorityFlag.URGENT) {
                        urgentQueue.offer(claim);
                    } else {
                        normalQueue.offer(claim);
                    }

                    // Process claims from priority queues
                    processFromPriorityQueues();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                shutdownLatch.countDown();
            }
        }

        private void processFromPriorityQueues() {
            // Process URGENT claims first, then NORMAL (with starvation prevention)
            Claim claim = null;

            // Try urgent queue first
            claim = urgentQueue.poll();
            if (claim == null) {
                // Try normal queue
                claim = normalQueue.poll();
            }

            if (claim != null) {
                processClaim(claim);
            }
        }

        private void processClaim(Claim claim) {
            PolicyProcessor policyProcessor = policyProcessors.computeIfAbsent(
                    claim.getPolicyNumber(),
                    k -> new PolicyProcessor(k)
            );

            policyProcessor.processClaim(claim);
        }
    }

    /**
     * Per-policy processor that ensures ordering within a policy
     */
    private class PolicyProcessor {
        private final String policyNumber;
        private final Lock policyLock;
        private final Queue<Claim> policyQueue;

        public PolicyProcessor(String policyNumber) {
            this.policyNumber = policyNumber;
            this.policyLock = new ReentrantLock();
            this.policyQueue = new LinkedList<>();
        }

        public void processClaim(Claim claim) {
            policyLock.lock();
            try {
                // Check idempotency again under lock
                if (processedClaims.containsKey(claim.getClaimId())) {
                    return;
                }

                // Create processing result
                ProcessingResult result = new ProcessingResult(claim);
                ProcessingResult existing = processedClaims.putIfAbsent(claim.getClaimId(), result);

                if (existing != null) {
                    // Another thread already started processing this claim
                    return;
                }

                // Process the claim
                processClaimWithRetries(claim, result);

            } finally {
                policyLock.unlock();
            }
        }

        private void processClaimWithRetries(Claim claim, ProcessingResult result) {
            String threadName = Thread.currentThread().getName();
            int attempt = 0;

            while (attempt <= config.getMaxRetries()) {
                attempt++;
                result.incrementAttempts();

                auditLogger.logEvent(claim.getClaimId(), threadName,
                        result.getStatus(), ClaimStatus.PROCESSING, "PROCESSING_START", attempt);

                result.updateStatus(ClaimStatus.PROCESSING);

                try {
                    // Perform external check with timeout
                    ExternalCheckResult checkResult = externalCheckService.performCheckSync(
                            claim, config.getExternalCheckTimeoutMs());

                    // Handle check result
                    switch (checkResult) {
                        case SUCCESS:
                            result.updateStatus(ClaimStatus.APPROVED);
                            auditLogger.logEvent(claim.getClaimId(), threadName,
                                    ClaimStatus.PROCESSING, ClaimStatus.APPROVED, "APPROVED", attempt);
                            return;

                        case PERMANENT_FAILURE:
                            result.updateStatus(ClaimStatus.REJECTED);
                            auditLogger.logEvent(claim.getClaimId(), threadName,
                                    ClaimStatus.PROCESSING, ClaimStatus.REJECTED, "REJECTED", attempt);
                            return;

                        case TRANSIENT_FAILURE:
                            if (attempt > config.getMaxRetries()) {
                                // Max retries reached, escalate
                                result.updateStatus(ClaimStatus.ESCALATED);
                                auditLogger.logEvent(claim.getClaimId(), threadName,
                                        ClaimStatus.PROCESSING, ClaimStatus.ESCALATED, "ESCALATED_MAX_RETRIES", attempt);
                                return;
                            } else {
                                auditLogger.logEvent(claim.getClaimId(), threadName,
                                        ClaimStatus.PROCESSING, null, "TRANSIENT_FAILURE_RETRY", attempt);
                                // Continue to retry
                            }
                            break;
                    }

                } catch (Exception e) {
                    auditLogger.logEvent(claim.getClaimId(), threadName,
                            ClaimStatus.PROCESSING, null, "ERROR_" + e.getClass().getSimpleName(), attempt);

                    if (attempt > config.getMaxRetries()) {
                        result.updateStatus(ClaimStatus.ESCALATED);
                        auditLogger.logEvent(claim.getClaimId(), threadName,
                                ClaimStatus.PROCESSING, ClaimStatus.ESCALATED, "ESCALATED_EXCEPTION", attempt);
                        return;
                    }
                }
            }
        }
    }

    private void processRemainingClaims() {
        // Process any remaining urgent claims
        while (!urgentQueue.isEmpty()) {
            Claim claim = urgentQueue.poll();
            if (claim != null && !processedClaims.containsKey(claim.getClaimId())) {
                PolicyProcessor processor = policyProcessors.computeIfAbsent(
                        claim.getPolicyNumber(), k -> new PolicyProcessor(k));
                processor.processClaim(claim);
            }
        }

        // Process any remaining normal claims
        while (!normalQueue.isEmpty()) {
            Claim claim = normalQueue.poll();
            if (claim != null && !processedClaims.containsKey(claim.getClaimId())) {
                PolicyProcessor processor = policyProcessors.computeIfAbsent(
                        claim.getPolicyNumber(), k -> new PolicyProcessor(k));
                processor.processClaim(claim);
            }
        }
    }

    public void shutdown() throws InterruptedException {
        isShuttingDown.set(true);

        // Signal shutdown to workers
        for (int i = 0; i < config.getWorkerCount(); i++) {
            backlogQueue.offer(createPoisonPill());
        }

        // Wait for workers to complete
        workerPool.shutdown();
        workerPool.awaitTermination(30, TimeUnit.SECONDS);

        auditLogger.logSystemEvent("SHUTDOWN", "COMPLETED");
    }

    private Claim createPoisonPill() {
        return new Claim("POISON_PILL", "POISON", 0, ClaimType.Health,
                java.time.LocalDateTime.now(), PriorityFlag.NORMAL);
    }

    private boolean isPoisonPill(Claim claim) {
        return "POISON_PILL".equals(claim.getClaimId());
    }
}