package com.hdfc.bank.model;

import java.util.Scanner;

public class HomeLoan extends Loan {
   private String propertyAddress;


// constructor added parameters
    public HomeLoan(double loanAmount, String customerName, int loanId, String propertyAddress) {
        super(loanAmount, customerName, loanId);
        this.propertyAddress = propertyAddress;
    }

    public String getPropertyAddress() {
        return propertyAddress;
    }

    public void setPropertyAddress(String propertyAddress) {
        this.propertyAddress = propertyAddress;
    }

    //emi formula

    @Override
    public double calculateEMI() {
        double interest=getLoanAmount()*0.9*2;
        double totalamount=getLoanAmount()+interest;
        return totalamount/24;

    }

    @Override
    public void printDetails() {
        super.printDetails();
        System.out.println("Home Address-" +propertyAddress);
    }
}
