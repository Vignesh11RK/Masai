package com.insurance.claims.core;

import com.insurance.claims.config.Configuration;
import com.insurance.claims.io.AuditLogger;
import com.insurance.claims.model.Claim;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Fraud detection system with automatic throttling.
 * Monitors suspicious claims and triggers system pause when threshold is exceeded.
 */
public class FraudDetector {

    private final Configuration config;
    private final AuditLogger auditLogger;

    // Sliding window for suspicious claims tracking
    private final ConcurrentLinkedQueue<Long> suspiciousTimestamps;
    private final AtomicInteger suspiciousClaimsCount;

    // Throttling state
    private final AtomicBoolean isThrottled;
    private final Lock throttleLock;
    private final Condition throttleCondition;

    // Monitoring thread
    private Thread monitorThread;
    private final AtomicBoolean shouldStop;

    public FraudDetector(Configuration config, AuditLogger auditLogger) {
        this.config = config;
        this.auditLogger = auditLogger;
        this.suspiciousTimestamps = new ConcurrentLinkedQueue<>();
        this.suspiciousClaimsCount = new AtomicInteger(0);
        this.isThrottled = new AtomicBoolean(false);
        this.throttleLock = new ReentrantLock();
        this.throttleCondition = throttleLock.newCondition();
        this.shouldStop = new AtomicBoolean(false);
    }

    /**
     * Start background monitoring for fraud detection
     */
    public void startMonitoring() {
        monitorThread = new Thread(this::monitorSuspiciousActivity, "FraudMonitor");
        monitorThread.start();
    }

    /**
     * Stop monitoring and cleanup
     */
    public void stopMonitoring() {
        shouldStop.set(true);
        if (monitorThread != null) {
            monitorThread.interrupt();
            try {
                monitorThread.join(1000); // Wait up to 1 second
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    /**
     * Check if a claim is suspicious and handle accordingly
     */
    public void checkClaim(Claim claim) {
        if (claim.isSuspicious(config.getSuspiciousClaimType(), config.getSuspiciousAmountThreshold())) {
            handleSuspiciousClaim(claim);
        }
    }

    /**
     * Wait if system is currently throttled
     */
    public void waitIfThrottled() throws InterruptedException {
        if (isThrottled.get()) {
            throttleLock.lock();
            try {
                while (isThrottled.get()) {
                    throttleCondition.await();
                }
            } finally {
                throttleLock.unlock();
            }
        }
    }

    private void handleSuspiciousClaim(Claim claim) {
        long currentTime = System.currentTimeMillis();
        suspiciousTimestamps.offer(currentTime);
        int count = suspiciousClaimsCount.incrementAndGet();

        // Log suspicious claim immediately
        System.out.printf("SUSPICIOUS CLAIM DETECTED: %s (Type: %s, Amount: %d)%n",
                claim.getClaimId(), claim.getClaimType(), claim.getClaimAmount());

        auditLogger.logSuspiciousClaim(claim.getClaimId(),
                claim.getClaimType() + "_" + claim.getClaimAmount());

        // Check if we need to throttle
        synchronized (this) {
            cleanOldTimestamps(currentTime);
            if (suspiciousTimestamps.size() > config.getSuspiciousThreshold()) {
                triggerThrottle();
            }
        }
    }

    private void monitorSuspiciousActivity() {
        while (!shouldStop.get()) {
            try {
                Thread.sleep(1000); // Check every second

                long currentTime = System.currentTimeMillis();
                synchronized (this) {
                    cleanOldTimestamps(currentTime);
                }

            } catch (InterruptedException e) {
                break;
            }
        }
    }

    private void cleanOldTimestamps(long currentTime) {
        long windowStart = currentTime - (config.getSuspiciousWindowSeconds() * 1000L);

        while (!suspiciousTimestamps.isEmpty()) {
            Long timestamp = suspiciousTimestamps.peek();
            if (timestamp != null && timestamp < windowStart) {
                suspiciousTimestamps.poll();
            } else {
                break;
            }
        }
    }

    private void triggerThrottle() {
        if (isThrottled.compareAndSet(false, true)) {
            System.out.printf("FRAUD ALERT: %d suspicious claims in %d seconds - THROTTLING for %d seconds%n",
                    suspiciousTimestamps.size(), config.getSuspiciousWindowSeconds(),
                    config.getThrottlePauseSeconds());

            auditLogger.logSystemEvent("THROTTLE", "START");

            // Start unthrottle timer
            Thread unthrottleThread = new Thread(() -> {
                try {
                    Thread.sleep(config.getThrottlePauseSeconds() * 1000L);

                    throttleLock.lock();
                    try {
                        isThrottled.set(false);
                        throttleCondition.signalAll();
                        System.out.println("THROTTLING ENDED - Resuming normal processing");
                        auditLogger.logSystemEvent("THROTTLE", "END");
                    } finally {
                        throttleLock.unlock();
                    }

                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }, "UnthrottleTimer");

            unthrottleThread.start();
        }
    }

    public int getSuspiciousClaimsCount() {
        return suspiciousClaimsCount.get();
    }

    public boolean isCurrentlyThrottled() {
        return isThrottled.get();
    }
}