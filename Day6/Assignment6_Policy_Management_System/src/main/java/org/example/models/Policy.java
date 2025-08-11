package org.example.models;

public abstract class Policy {
    protected String policyId;
    protected double premiumAmount;
    protected int termYears;

    // GETTER SETTERS

    public String getPolicyId() {
        return policyId;
    }

    public void setPolicyId(String policyId) {
        this.policyId = policyId;
    }

    public double getPremiumAmount() {
        return premiumAmount;
    }

    public void setPremiumAmount(double premiumAmount) {
        this.premiumAmount = premiumAmount;
    }

    public int getTermYears() {
        return termYears;
    }

    public void setTermYears(int termYears) {
        this.termYears = termYears;
    }
    // CONSTRUCTOR

    public Policy(String policyId, double premiumAmount, int termYears) {
        this.policyId = policyId;
        this.premiumAmount = premiumAmount;
        this.termYears = termYears;
    }

    public abstract double calculateMaturityAmount();



}
