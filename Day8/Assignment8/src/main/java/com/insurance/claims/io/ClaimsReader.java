package com.insurance.claims.io;

import com.insurance.claims.model.Claim;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Reads insurance claims from CSV file
 */
public class ClaimsReader {

    /**
     * Reads all claims from the specified CSV file.
     * Skips the header row and handles parsing errors gracefully.
     */
    public List<Claim> readClaims(String filename) throws IOException {
        List<Claim> claims = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line = reader.readLine(); // Skip header
            if (line == null) {
                throw new IOException("Empty CSV file: " + filename);
            }

            int lineNumber = 1;
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                line = line.trim();

                if (line.isEmpty()) {
                    continue; // Skip empty lines
                }

                try {
                    Claim claim = Claim.fromCsvLine(line);
                    claims.add(claim);
                } catch (Exception e) {
                    System.err.printf("Warning: Failed to parse line %d: %s (Error: %s)%n",
                            lineNumber, line, e.getMessage());
                    // Continue processing other lines
                }
            }
        }

        return claims;
    }
}