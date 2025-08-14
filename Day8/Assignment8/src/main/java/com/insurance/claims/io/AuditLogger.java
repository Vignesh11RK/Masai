package com.insurance.claims.io;

import com.insurance.claims.model.ClaimStatus;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Thread-safe audit logger for claim processing events.
 * Ensures atomic logging with no interleaved lines.
 */
public class AuditLogger {

    private final BufferedWriter writer;
    private final Lock writeLock = new ReentrantLock();
    private final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    public AuditLogger(String filename) throws IOException {
        this.writer = new BufferedWriter(new FileWriter(filename, true));

        // Log session start
        logEvent("SYSTEM", "SESSION", null, null, "SESSION_START", 0);
    }

    /**
     * Log a claim processing event atomically
     */
    public void logEvent(String claimId, String threadName, ClaimStatus previousStatus,
                         ClaimStatus newStatus, String event, int attemptNumber) {
        writeLock.lock();
        try {
            String timestamp = LocalDateTime.now().format(timeFormatter);
            String prevStatusStr = previousStatus != null ? previousStatus.toString() : "NULL";
            String newStatusStr = newStatus != null ? newStatus.toString() : event;

            String logLine = String.format("%s|%s|%s|%s|%s|%d%n",
                    timestamp, claimId, threadName,
                    prevStatusStr, newStatusStr, attemptNumber);

            writer.write(logLine);
            writer.flush(); // Ensure immediate write for audit purposes

        } catch (IOException e) {
            System.err.println("Failed to write audit log: " + e.getMessage());
        } finally {
            writeLock.unlock();
        }
    }

    /**
     * Log suspicious claim detection
     */
    public void logSuspiciousClaim(String claimId, String reason) {
        logEvent(claimId, Thread.currentThread().getName(), null, null,
                "SUSPICIOUS_" + reason, 0);
    }

    /**
     * Log system events (throttling, shutdown, etc.)
     */
    public void logSystemEvent(String event, String details) {
        logEvent("SYSTEM", Thread.currentThread().getName(), null, null,
                event + "_" + details, 0);
    }

    /**
     * Close the logger and release resources
     */
    public void close() {
        writeLock.lock();
        try {
            logEvent("SYSTEM", "SESSION", null, null, "SESSION_END", 0);
            writer.close();
        } catch (IOException e) {
            System.err.println("Error closing audit logger: " + e.getMessage());
        } finally {
            writeLock.unlock();
        }
    }
}