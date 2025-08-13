package com.insurance.claims.core;

import com.insurance.claims.io.AuditLogger;
import com.insurance.claims.model.ClaimStatus;
import com.insurance.claims.model.ProcessingResult;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.concurrent.atomic.LongAdder;

/**
 * Generates final summary report of claims processing
 */
public class ReportGenerator {

    private final AuditLogger auditLogger;

    public ReportGenerator(AuditLogger auditLogger) {
        this.auditLogger = auditLogger;
    }

    /**
     * Generate comprehensive summary report
     */
    public void generateReport(Collection<ProcessingResult> results,
                               int suspiciousClaimsCount,
                               long processingTimeMs) throws IOException {

        ReportData data = calculateReportData(results);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("summary.txt"))) {
            writer.write("INSURANCE CLAIMS PROCESSING SUMMARY REPORT\n");
            writer.write("==========================================\n\n");

            writer.write(String.format("Processing completed at: %s\n",
                    java.time.LocalDateTime.now()));
            writer.write(String.format("Total wall-clock time: %.2f seconds\n\n",
                    processingTimeMs / 1000.0));

            writer.write("CLAIMS STATISTICS:\n");
            writer.write(String.format("  Total unique claims processed: %d\n", data.totalClaims));
            writer.write(String.format("  Approved claims: %d\n", data.approvedClaims));
            writer.write(String.format("  Escalated claims: %d\n", data.escalatedClaims));
            writer.write(String.format("  Rejected claims: %d\n", data.rejectedClaims));
            writer.write(String.format("  Still processing: %d\n", data.processingClaims));
            writer.write("\n");

            writer.write("FINANCIAL SUMMARY:\n");
            writer.write(String.format("  Total amount paid (Approved): ₹%,d\n", data.totalPaid));
            writer.write(String.format("  Average claim amount: ₹%,.2f\n", data.averageClaimAmount));
            writer.write("\n");

            writer.write("PROCESSING EFFICIENCY:\n");
            writer.write(String.format("  Average processing attempts per claim: %.2f\n", data.averageAttempts));
            writer.write(String.format("  Success rate: %.1f%%\n", data.successRate));
            writer.write("\n");

            writer.write("FRAUD DETECTION:\n");
            writer.write(String.format("  Suspicious claims detected: %d\n", suspiciousClaimsCount));
            writer.write(String.format("  Fraud detection rate: %.2f%%\n",
                    (suspiciousClaimsCount * 100.0) / Math.max(1, data.totalClaims)));
            writer.write("\n");

            writer.write("STATUS BREAKDOWN:\n");
            writer.write(String.format("  APPROVED: %d (%.1f%%)\n",
                    data.approvedClaims,
                    (data.approvedClaims * 100.0) / Math.max(1, data.totalClaims)));
            writer.write(String.format("  ESCALATED: %d (%.1f%%)\n",
                    data.escalatedClaims,
                    (data.escalatedClaims * 100.0) / Math.max(1, data.totalClaims)));
            writer.write(String.format("  REJECTED: %d (%.1f%%)\n",
                    data.rejectedClaims,
                    (data.rejectedClaims * 100.0) / Math.max(1, data.totalClaims)));

            if (data.processingClaims > 0) {
                writer.write(String.format("  PROCESSING: %d (%.1f%%)\n",
                        data.processingClaims,
                        (data.processingClaims * 100.0) / Math.max(1, data.totalClaims)));
            }
        }

        // Log report generation
        auditLogger.logSystemEvent("REPORT", "GENERATED");

        System.out.println("Summary report generated: summary.txt");
    }

    private ReportData calculateReportData(Collection<ProcessingResult> results) {
        ReportData data = new ReportData();

        LongAdder totalAmount = new LongAdder();
        LongAdder totalApprovedAmount = new LongAdder();
        LongAdder totalAttempts = new LongAdder();

        for (ProcessingResult result : results) {
            data.totalClaims++;
            totalAmount.add(result.getClaimAmount());
            totalAttempts.add(result.getAttempts());

            switch (result.getStatus()) {
                case APPROVED:
                    data.approvedClaims++;
                    totalApprovedAmount.add(result.getClaimAmount());
                    break;
                case ESCALATED:
                    data.escalatedClaims++;
                    break;
                case REJECTED:
                    data.rejectedClaims++;
                    break;
                case PROCESSING:
                    data.processingClaims++;
                    break;
                default:
                    // PENDING claims shouldn't normally exist at this point
                    break;
            }
        }

        data.totalPaid = totalApprovedAmount.longValue();
        data.averageAttempts = data.totalClaims > 0 ?
                (double) totalAttempts.longValue() / data.totalClaims : 0.0;
        data.averageClaimAmount = data.totalClaims > 0 ?
                (double) totalAmount.longValue() / data.totalClaims : 0.0;
        data.successRate = data.totalClaims > 0 ?
                (data.approvedClaims * 100.0) / data.totalClaims : 0.0;

        return data;
    }

    /**
     * Internal data class for report calculations
     */
    private static class ReportData {
        int totalClaims = 0;
        int approvedClaims = 0;
        int escalatedClaims = 0;
        int rejectedClaims = 0;
        int processingClaims = 0;
        long totalPaid = 0;
        double averageAttempts = 0.0;
        double averageClaimAmount = 0.0;
        double successRate = 0.0;
    }
}