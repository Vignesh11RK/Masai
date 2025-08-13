package com.insurance.claims.core;

import com.insurance.claims.model.Claim;
import com.insurance.claims.model.ExternalCheckResult;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Simulates external checks for insurance claims.
 * Randomly produces success, transient failure, or permanent failure results.
 * Can simulate slow responses that need to be timed out.
 */
public class ExternalCheckService {

    private final Random random = new Random();

    /**
     * Performs an external check with timeout support
     */
    public CompletableFuture<ExternalCheckResult> performCheck(Claim claim, long timeoutMs) {
        return CompletableFuture.supplyAsync(() -> {
                    // Simulate variable processing time (0-8 seconds)
                    int processingTimeMs = random.nextInt(8000);

                    try {
                        Thread.sleep(processingTimeMs);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return ExternalCheckResult.TRANSIENT_FAILURE;
                    }

                    // Simulate different outcomes:
                    // 60% success, 25% transient failure, 15% permanent failure
                    int outcome = random.nextInt(100);

                    if (outcome < 60) {
                        return ExternalCheckResult.SUCCESS;
                    } else if (outcome < 85) {
                        return ExternalCheckResult.TRANSIENT_FAILURE;
                    } else {
                        return ExternalCheckResult.PERMANENT_FAILURE;
                    }
                }).orTimeout(timeoutMs, TimeUnit.MILLISECONDS)
                .exceptionally(throwable -> {
                    if (throwable instanceof TimeoutException) {
                        return ExternalCheckResult.TRANSIENT_FAILURE; // Timeout = transient failure
                    }
                    return ExternalCheckResult.TRANSIENT_FAILURE;
                });
    }

    /**
     * Synchronous version with timeout handling
     */
    public ExternalCheckResult performCheckSync(Claim claim, long timeoutMs) {
        try {
            return performCheck(claim, timeoutMs).get();
        } catch (Exception e) {
            return ExternalCheckResult.TRANSIENT_FAILURE;
        }
    }
}