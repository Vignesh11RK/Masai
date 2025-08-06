package com.hdfc.bank.services;

import com.hdfc.bank.model.HomeLoan;
import com.hdfc.bank.model.Loan;
import com.hdfc.bank.model.PersonalLoan;

public class LoanService {
    public void printEMIDetails(Loan loan) {// object passed

        if(loan instanceof HomeLoan){
            HomeLoan hl=(HomeLoan)loan;
            hl.printDetails();

        }else if (loan instanceof PersonalLoan){
            PersonalLoan pl =(PersonalLoan) loan;
            pl.printDetails();
        }
        System.out.println("Monthly emi " +loan.calculateEMI());


    }

}
