package com.hdfc.bank.main;

import com.hdfc.bank.model.HomeLoan;
import com.hdfc.bank.model.Loan;
import com.hdfc.bank.services.LoanService;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        Loan Hloan=new HomeLoan(50000,"Rakesh",101,"Santacruz");
        Loan Ploan=new HomeLoan(50000,"Rakesh",101,"Santacruz");

        LoanService ls=new LoanService();
        ls.printEMIDetails(Hloan);
        ls.printEMIDetails(Ploan);
        }
    }
