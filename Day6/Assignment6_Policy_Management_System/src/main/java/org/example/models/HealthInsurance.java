package org.example.models;

public class HealthInsurance extends Policy {
    public HealthInsurance(String policyId, double premiumAmount, int termYears) {
        super(policyId, premiumAmount, termYears);
    }

    @Override
    public double calculateMaturityAmount() {
        return premiumAmount * termYears * 1.05;
    }
}
