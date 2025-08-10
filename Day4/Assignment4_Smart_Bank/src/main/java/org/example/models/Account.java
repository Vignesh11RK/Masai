package org.example.models;

public abstract class Account {
    // fields

    protected String accountNumber;
    protected String customerName;
    protected double balance;

    // getters and setters

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    // constructor
    public Account(String accountNumber, String customerName, double balance) {
        this.accountNumber = accountNumber;
        this.customerName = customerName;
        this.balance = balance;
    }

    public void printAccountDetails(){
        System.out.println("Here goes your account details");
    }

    public abstract double calculateMonthlyInterest();


}
