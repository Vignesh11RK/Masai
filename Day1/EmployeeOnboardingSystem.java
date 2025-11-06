// EmployeeOnboardingSystem.java
// HDFC Life – Employee Onboarding System Assignment

public class EmployeeOnboardingSystem {

    public static void main(String[] args) {

        // 1️⃣ Employee Profile Setup
        final String COMPANY_NAME = "HDFC Life";
        String name = "Priya Mehra";
        int age = 22;
        String city = "Pune";
        float joiningPercentage = 86.7f;
        char performanceGrade;

        System.out.println("Welcome to " + COMPANY_NAME + "!");
        System.out.println("Employee: " + name + " | Age: " + age + " | City: " + city);
        System.out.println("Joining Percentage: " + joiningPercentage);

        // 2️⃣ Performance Rating
        if (joiningPercentage > 90) {
            performanceGrade = 'A';
        } else if (joiningPercentage >= 75) {
            performanceGrade = 'B';
        } else if (joiningPercentage >= 60) {
            performanceGrade = 'C';
        } else {
            performanceGrade = 'D';
        }

        System.out.println("Performance Grade: " + performanceGrade);

        // HR Comments using switch-case
        switch (performanceGrade) {
            case 'A':
                System.out.println("HR Feedback: Star Performer");
                break;
            case 'B':
                System.out.println("HR Feedback: Strong Start");
                break;
            case 'C':
                System.out.println("HR Feedback: Satisfactory");
                break;
            case 'D':
                System.out.println("HR Feedback: Needs Mentorship");
                break;
            default:
                System.out.println("HR Feedback: Invalid Grade");
        }

        // 3️⃣ HDFC Utility Tools
        System.out.println("\n--- Prime Employee Codes ---");
        for (int i = 2; i <= 50; i++) {
            boolean isPrime = true;
            for (int j = 2; j <= i / 2; j++) {
                if (i % j == 0) {
                    isPrime = false;
                    break; // Not a prime
                }
            }
            if (!isPrime) continue;
            System.out.print(i + " ");
        }

        // 3️⃣(b) ID Card Pattern Generator
        System.out.println("\n\n--- Badge Pattern ---");
        for (int i = 1; i <= 5; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }

        // 3️⃣(c) Compensation Calculator
        float monthlyCTC = 58000.50f;
        int monthlyCTCInt = (int) monthlyCTC;
        float annualCTC = monthlyCTC * 12;
        annualCTC += 10000; // Bonus using compound assignment

        System.out.println("\nMonthly CTC (float): " + monthlyCTC);
        System.out.println("Monthly CTC (int): " + monthlyCTCInt);
        System.out.println("Annual CTC (after bonus): " + (int) annualCTC);
    }
}
