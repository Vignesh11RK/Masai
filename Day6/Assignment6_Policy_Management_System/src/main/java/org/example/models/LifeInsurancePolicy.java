package org.example.models;

public class LifeInsurancePolicy extends Policy {
    public LifeInsurancePolicy(String policyId, double premiumAmount, int termYears) {
        super(policyId, premiumAmount, termYears);
    }

    @Override
    public double calculateMaturityAmount() {
        return premiumAmount * termYears * 1.1;
    }
}
