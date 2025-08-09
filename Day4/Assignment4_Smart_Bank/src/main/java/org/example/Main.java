package org.example;


import org.example.models.Account;
import org.example.models.SavingsAccount;


import org.example.transactions.UPITransaction;
import org.example.transactions.CardTransaction;
import org.example.interfaces.TransactionProcessor;



//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
//        Account acc = new SavingsAccount("HDF00011", "Vignesh", 10000);
//        acc.printAccountDetails();

        TransactionProcessor upi = new UPITransaction();
        TransactionProcessor card = new CardTransaction();

        System.out.println("Testing Transactions:\n");

        upi.process(500);
        upi.printReceipt();

        card.process(1000);
        card.printReceipt();

        upi.refund(200);
        card.refund(300);

    }
}