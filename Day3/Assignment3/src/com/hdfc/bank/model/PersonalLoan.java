package com.hdfc.bank.model;

public class PersonalLoan extends Loan {
    private String purpose;

    public PersonalLoan(double loanAmount, String customerName, int loanId, String purpose) {
        super(loanAmount, customerName, loanId);
        this.purpose = purpose;
    }

    public double calculateEMI() {
        double interest=getLoanAmount()*0.9*2;
        double totalamount=getLoanAmount()+interest;
        return totalamount/24;

    }

    @Override
    public void printDetails() {
        super.printDetails();
        System.out.println("Home Address-" +loanAmount);
    }


}
