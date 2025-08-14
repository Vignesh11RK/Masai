package com.insurance.claims.model;

/**
 * Represents the processing result and history of a claim
 */
public class ProcessingResult {
    private final String claimId;
    private final String policyNumber;
    private final int claimAmount;
    private final ClaimType claimType;
    private final PriorityFlag priorityFlag;
    private volatile ClaimStatus status;
    private volatile int attempts;
    private final long firstProcessedTime;
    private volatile long lastProcessedTime;

    public ProcessingResult(Claim claim) {
        this.claimId = claim.getClaimId();
        this.policyNumber = claim.getPolicyNumber();
        this.claimAmount = claim.getClaimAmount();
        this.claimType = claim.getClaimType();
        this.priorityFlag = claim.getPriorityFlag();
        this.status = ClaimStatus.PENDING;
        this.attempts = 0;
        this.firstProcessedTime = System.currentTimeMillis();
        this.lastProcessedTime = firstProcessedTime;
    }

    public synchronized void updateStatus(ClaimStatus newStatus) {
        this.status = newStatus;
        this.lastProcessedTime = System.currentTimeMillis();
    }

    public synchronized void incrementAttempts() {
        this.attempts++;
        this.lastProcessedTime = System.currentTimeMillis();
    }

    // Getters
    public String getClaimId() { return claimId; }
    public String getPolicyNumber() { return policyNumber; }
    public int getClaimAmount() { return claimAmount; }
    public ClaimType getClaimType() { return claimType; }
    public PriorityFlag getPriorityFlag() { return priorityFlag; }
    public ClaimStatus getStatus() { return status; }
    public int getAttempts() { return attempts; }
    public long getFirstProcessedTime() { return firstProcessedTime; }
    public long getLastProcessedTime() { return lastProcessedTime; }
}
