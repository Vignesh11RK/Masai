package com.insurance.claims.model;

/**
 * Processing status of a claim
 */
public enum ClaimStatus {
    PENDING,     // Initial state
    PROCESSING,  // Being processed
    APPROVED,    // External check passed
    ESCALATED,   // Needs manual review
    REJECTED     // External check failed permanently
}
