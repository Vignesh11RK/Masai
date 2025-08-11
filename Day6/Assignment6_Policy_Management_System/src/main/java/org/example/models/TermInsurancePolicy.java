package org.example.models;

public class TermInsurancePolicy extends Policy{
    public TermInsurancePolicy(String policyId, double premiumAmount, int termYears) {
        super(policyId, premiumAmount, termYears);
    }

    @Override
    public double calculateMaturityAmount() {
        return premiumAmount * termYears ;
    }
}
