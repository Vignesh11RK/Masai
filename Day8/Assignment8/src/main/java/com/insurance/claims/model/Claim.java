package com.insurance.claims.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Represents an insurance claim
 */
public class Claim {
    private final String claimId;
    private final String policyNumber;
    private final int claimAmount;
    private final ClaimType claimType;
    private final LocalDateTime timestamp;
    private final PriorityFlag priorityFlag;

    public Claim(String claimId, String policyNumber, int claimAmount,
                 ClaimType claimType, LocalDateTime timestamp, PriorityFlag priorityFlag) {
        this.claimId = claimId;
        this.policyNumber = policyNumber;
        this.claimAmount = claimAmount;
        this.claimType = claimType;
        this.timestamp = timestamp;
        this.priorityFlag = priorityFlag;
    }

    public static Claim fromCsvLine(String line) {
        String[] parts = line.split(",");
        if (parts.length != 6) {
            throw new IllegalArgumentException("Invalid CSV line: " + line);
        }

        return new Claim(
                parts[0].trim(),
                parts[1].trim(),
                Integer.parseInt(parts[2].trim()),
                ClaimType.valueOf(parts[3].trim()),
                LocalDateTime.parse(parts[4].trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                PriorityFlag.valueOf(parts[5].trim())
        );
    }

    public boolean isSuspicious(String suspiciousType, int amountThreshold) {
        return this.claimType.toString().equals(suspiciousType) &&
                this.claimAmount >= amountThreshold;
    }

    // Getters
    public String getClaimId() { return claimId; }
    public String getPolicyNumber() { return policyNumber; }
    public int getClaimAmount() { return claimAmount; }
    public ClaimType getClaimType() { return claimType; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public PriorityFlag getPriorityFlag() { return priorityFlag; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Claim)) return false;
        Claim claim = (Claim) o;
        return Objects.equals(claimId, claim.claimId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(claimId);
    }

    @Override
    public String toString() {
        return String.format("Claim{id=%s, policy=%s, amount=%d, type=%s, priority=%s}",
                claimId, policyNumber, claimAmount, claimType, priorityFlag);
    }
}

