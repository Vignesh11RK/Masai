package com.insurance.claims;

import com.insurance.claims.config.Configuration;
import com.insurance.claims.core.ClaimsProcessor;
import com.insurance.claims.core.FraudDetector;
import com.insurance.claims.core.ReportGenerator;
import com.insurance.claims.io.AuditLogger;
import com.insurance.claims.io.ClaimsReader;
import com.insurance.claims.model.Claim;
import com.insurance.claims.model.ProcessingResult;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Main application class for the Insurance Claims Processing System.
 *
 * This system processes insurance claims concurrently while maintaining:
 * - Per-policy ordering (claims for same policy processed in order)
 * - Priority processing (URGENT claims processed before NORMAL)
 * - Idempotency (duplicate claims processed only once)
 * - Fraud detection with automatic throttling
 * - Comprehensive audit logging
 */
public class ClaimsProcessorApp {

    private final Configuration config;
    private final AuditLogger auditLogger;
    private final ClaimsProcessor processor;
    private final FraudDetector fraudDetector;
    private final ReportGenerator reportGenerator;

    // Thread-safe tracking of processing results
    private final ConcurrentMap<String, ProcessingResult> processedClaims;

    public ClaimsProcessorApp(Configuration config) throws IOException {
        this.config = config;
        this.auditLogger = new AuditLogger("audit.log");
        this.processedClaims = new ConcurrentHashMap<>();
        this.fraudDetector = new FraudDetector(config, auditLogger);
        this.processor = new ClaimsProcessor(config, auditLogger, processedClaims, fraudDetector);
        this.reportGenerator = new ReportGenerator(auditLogger);
    }

    public void processAllClaims() throws IOException, InterruptedException {
        System.out.println("Starting claims processing...");
        long startTime = System.currentTimeMillis();

        // Setup shutdown hook for graceful shutdown
        setupShutdownHook();

        try {
            // Read claims from CSV
            ClaimsReader reader = new ClaimsReader();
            List<Claim> claims = reader.readClaims("claims.csv");
            System.out.printf("Loaded %d claims from CSV%n", claims.size());

            // Start fraud detection monitoring
            fraudDetector.startMonitoring();

            // Process all claims
            processor.processAllClaims(claims);

            // Generate final report
            long endTime = System.currentTimeMillis();
            long processingTime = endTime - startTime;

            reportGenerator.generateReport(processedClaims.values(),
                    fraudDetector.getSuspiciousClaimsCount(),
                    processingTime);

            System.out.printf("Processing completed in %d ms%n", processingTime);
            System.out.printf("Processed %d unique claims%n", processedClaims.size());

        } finally {
            // Cleanup resources
            fraudDetector.stopMonitoring();
            auditLogger.close();
        }
    }

    private void setupShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Shutting down ...");
            try {
                processor.shutdown();
                fraudDetector.stopMonitoring();
                auditLogger.close();
            } catch (Exception e) {
                System.err.println("Error during shutdown: " + e.getMessage());
            }
        }));
    }

    public static void main(String[] args) {
        try {
            // Load configuration
            Configuration config = Configuration.loadFromArgs(args);
            System.out.println("Configuration loaded: " + config);

            // Create and run application
            ClaimsProcessorApp app = new ClaimsProcessorApp(config);
            app.processAllClaims();

        } catch (Exception e) {
            System.err.println("Application failed: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }
}