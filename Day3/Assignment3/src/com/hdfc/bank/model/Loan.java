package com.hdfc.bank.model;

public abstract class Loan {

    private int loanId;
    protected String customerName;
    protected double loanAmount;

    public int getLoanId() {
        return loanId;
    }

    public void setLoanId(int loanId) {
        this.loanId = loanId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public Loan(double loanAmount, String customerName, int loanId) {
        this.loanAmount = loanAmount;
        this.customerName = customerName;
        this.loanId = loanId;
    }

  // Abstract Method creation
    public abstract double calculateEMI();


    public void printDetails(){
        System.out.println("Loan ID"+loanId);
        System.out.println("Customer Name");
        System.out.println("Loan Amount");
    }

//loan detailsfor tostring()
    @Override
    public String toString() {
        return "Loan{" +
                "loanId=" + loanId +
                ", customerName='" + customerName + '\'' +
                ", loanAmount=" + loanAmount +
                '}';
    }
}
