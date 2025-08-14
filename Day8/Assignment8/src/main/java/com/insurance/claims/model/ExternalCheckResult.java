package com.insurance.claims.model;

/**
 * Result of external check
 */
public enum ExternalCheckResult {
    SUCCESS,           // Check passed
    TRANSIENT_FAILURE, // Temporary failure, should retry
    PERMANENT_FAILURE  // Permanent failure, reject claim
}
