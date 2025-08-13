package org.example.util;

import java.util.Scanner;

public class InputUtil {
    private static Scanner sc = new Scanner(System.in);

    public static String readLine(String prompt) {
        System.out.print(prompt);
        return sc.nextLine().trim();
    }

    public static int readInt(String prompt, int min, int max) {
        while (true) {
            try {
                int val = Integer.parseInt(readLine(prompt));
                if (val >= min && val <= max) return val;
            } catch (NumberFormatException ignored) { }
            System.out.printf("Please enter a number between %d and %d%n", min, max);
        }
    }
}
