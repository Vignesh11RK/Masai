package com.vignesh;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class WordFrequencyAnalyzer {

    private static final String INPUT_FILE = "input.txt";
    private static final String OUTPUT_DIR = "target/output";
    private static final String OUTPUT_FILE = OUTPUT_DIR + "/output.txt";

    public static void main(String[] args) {
        try {
            Map<String, Integer> wordFrequencies = processInputFile();
            List<Map.Entry<String, Integer>> sortedList = sortFrequencies(wordFrequencies);
            writeOutputFile(sortedList);
            System.out.println("Word frequency analysis complete. Output written to " + OUTPUT_FILE);
        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static Map<String, Integer> processInputFile() throws IOException {
        Map<String, Integer> wordFrequencies = new HashMap<>();
        ClassLoader classLoader = WordFrequencyAnalyzer.class.getClassLoader();
        URL resource = classLoader.getResource(INPUT_FILE);
        if (resource == null) {
            throw new FileNotFoundException("Input file not found in resources: " + INPUT_FILE);
        }

        try (InputStream inputStream = resource.openStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.toLowerCase().replaceAll("[^a-zA-Z0-9 ]", "").split("\\s+");
                for (String word : words) {
                    if (!word.isEmpty()) {
                        wordFrequencies.put(word, wordFrequencies.getOrDefault(word, 0) + 1);
                    }
                }
            }
        }
        return wordFrequencies;
    }
    private static List<Map.Entry<String, Integer>> sortFrequencies(Map<String, Integer> wordFrequencies) {
        return wordFrequencies.entrySet()
                .stream()
                .sorted((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()))
                .collect(Collectors.toList());
    }
    private static void writeOutputFile(List<Map.Entry<String, Integer>> sortedList) throws IOException {
        Path outputDirPath = Paths.get(OUTPUT_DIR);
        if (!Files.exists(outputDirPath)) {
            Files.createDirectories(outputDirPath);
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(OUTPUT_FILE))) {
            for (Map.Entry<String, Integer> entry : sortedList) {
                writer.write(entry.getKey() + ": " + entry.getValue());
                writer.newLine();
            }
        }
    }
}


//Converts all text to lowercase
//
//Removes punctuation
//
//Splits on whitespace
//
//Counts each word
//
//Sorts by frequency (high --> low)
//
//Writes to target/output/output.txt