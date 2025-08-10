package org.example.interfaces;

import org.example.util.Logger;

public interface TransactionProcessor extends Logger {
    void process(double amount);
    boolean refund(double amount);

    default void printReceipt(){
      //  System.out.println("Transaction Complete");
        logInfo("Transaction Complete");

    }

    static boolean validate(double amount){
        return amount>0;
    }




}
