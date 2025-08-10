package org.example.enums;

public enum AccountType {
    SAVINGS(4),CURRENT(3), LOAN(10);

    private final double interestRate;

    AccountType(double interestRate) {
        this.interestRate = interestRate;
    }

     public double getInterestRate(){
        return interestRate;
    }

}
