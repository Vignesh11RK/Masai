package com.insurance.claims.config;

/**
 * Configuration class for the Claims Processing System.
 * All configurable parameters are defined here with sensible defaults.
 */
public class Configuration {

    // Worker pool configuration
    private final int workerCount;
    private final int backlogCapacity;

    // External check configuration
    private final long externalCheckTimeoutMs;
    private final int maxRetries;

    // Fraud detection configuration
    private final int suspiciousWindowSeconds;
    private final int suspiciousThreshold;
    private final int throttlePauseSeconds;

    // Suspicious claim criteria
    private final String suspiciousClaimType;
    private final int suspiciousAmountThreshold;

    public Configuration(int workerCount, int backlogCapacity,
                         long externalCheckTimeoutMs, int maxRetries,
                         int suspiciousWindowSeconds, int suspiciousThreshold,
                         int throttlePauseSeconds, String suspiciousClaimType,
                         int suspiciousAmountThreshold) {
        this.workerCount = workerCount;
        this.backlogCapacity = backlogCapacity;
        this.externalCheckTimeoutMs = externalCheckTimeoutMs;
        this.maxRetries = maxRetries;
        this.suspiciousWindowSeconds = suspiciousWindowSeconds;
        this.suspiciousThreshold = suspiciousThreshold;
        this.throttlePauseSeconds = throttlePauseSeconds;
        this.suspiciousClaimType = suspiciousClaimType;
        this.suspiciousAmountThreshold = suspiciousAmountThreshold;
    }

    /**
     * Create configuration with default values
     */
    public static Configuration defaultConfig() {
        return new Configuration(
                8,          // workerCount
                100,        // backlogCapacity
                5000,       // externalCheckTimeoutMs (5 seconds)
                3,          // maxRetries
                30,         // suspiciousWindowSeconds
                5,          // suspiciousThreshold
                2,          // throttlePauseSeconds
                "Accident", // suspiciousClaimType
                400000      // suspiciousAmountThreshold
        );
    }

    /**
     * Load configuration from command line arguments
     */
    public static Configuration loadFromArgs(String[] args) {
        Configuration defaults = defaultConfig();

        // For simplicity, using defaults. In a real system, you'd parse args
        // or load from a properties file
        return defaults;
    }

    // Getters
    public int getWorkerCount() { return workerCount; }
    public int getBacklogCapacity() { return backlogCapacity; }
    public long getExternalCheckTimeoutMs() { return externalCheckTimeoutMs; }
    public int getMaxRetries() { return maxRetries; }
    public int getSuspiciousWindowSeconds() { return suspiciousWindowSeconds; }
    public int getSuspiciousThreshold() { return suspiciousThreshold; }
    public int getThrottlePauseSeconds() { return throttlePauseSeconds; }
    public String getSuspiciousClaimType() { return suspiciousClaimType; }
    public int getSuspiciousAmountThreshold() { return suspiciousAmountThreshold; }

    @Override
    public String toString() {
        return String.format(
                "Configuration{workers=%d, backlog=%d, timeout=%dms, retries=%d, " +
                        "suspiciousWindow=%ds, suspiciousThreshold=%d, throttlePause=%ds}",
                workerCount, backlogCapacity, externalCheckTimeoutMs, maxRetries,
                suspiciousWindowSeconds, suspiciousThreshold, throttlePauseSeconds
        );
    }
}