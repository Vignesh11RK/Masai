package org.example;

import org.example.enums.AccountType;
import org.example.interfaces.TransactionProcessor;
import org.example.transactions.CardTransaction;
import org.example.transactions.UPITransaction;
import org.example.interfaces.InterestCalculator;
import org.example.util.MessagePrinter;
import org.example.util.Constants;
import org.example.models.Account;
import org.example.models.SavingsAccount;




import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.

        TransactionProcessor upi = new UPITransaction();
        TransactionProcessor card = new CardTransaction();

        System.out.println("Testing Transactions:\n");

        upi.process(500);
        upi.printReceipt();

        card.process(1000);
        card.printReceipt();

        upi.refund(200);
        card.refund(300);

        // Choosing account type
        AccountType selectedType = AccountType.SAVINGS;

        // Use switch-case to print benefit
        switch (selectedType) {
            case SAVINGS:
                System.out.println("Saving Account: Earns interest, safe and ideal for saving.");
                break;
            case CURRENT:
                System.out.println("Current Account: No interest, high liquidity for businesses.");
                break;
            case LOAN:
                System.out.println("Loan Account: Borrow funds, repay with interest.");
                break;
        }

        System.out.println("\n All Account Types and Their Interest Rates:\n");

        // Loop over enum values and print each with interest rate
        for (AccountType type : AccountType.values()) {
            System.out.println(" " + type.name() + " account has an interest rate of " + type.getInterestRate() + "%");
        }


        // Simple interest Lambda
        InterestCalculator simpleInterest=(principal,rate,years) ->(principal*rate*years)/100;

        //Compound Interest
        InterestCalculator compoundInterest=(principal,rate,years) -> principal*(Math.pow(1+rate/100,years)-1);

        double principal = 10000;
        double rate = 5;
        int years = 3;

        double si=simpleInterest.calculate(principal,rate,years);
        double ci=compoundInterest.calculate(principal,rate,years);

        System.out.println("Simple Interest is : "+si);
        System.out.println("Compound Interest is : "+ci);

        MessagePrinter.printMessages(
                " SmartBank Transaction Summary:",
                " UPI Payment: ₹500",
                " Card Payment: ₹1000",
                " Refunds processed successfully",
                "Thank you for using SmartBank!");

         //Create and add Transactions
        List<TransactionProcessor> transactions=new ArrayList<>();
        transactions.add(new UPITransaction());
        transactions.add(new CardTransaction());
        transactions.add(new UPITransaction());
        transactions.add(new CardTransaction());
        transactions.add(new UPITransaction());


        transactions.get(0).refund(100);  // refund success
        transactions.get(1).refund(0);    // refund fail
        transactions.get(2).refund(50);   // refund success
        transactions.get(3).refund(75);   // refund success
        transactions.get(4).refund(-20);  // invalid refund

        System.out.println("Classes that successfully refunded:");

        System.out.println("\n Classes that successfully refunded:");

        // remember that I should npt use the same variables for different blocks

        transactions.stream()
                .filter(txn -> txn.refund(10))  // filter successful refunds
                .map(txn -> txn.getClass().getSimpleName()) // get class names
                .distinct()
                .forEach(System.out::println);

        //Count how many UPITransaction objects exist

        long upiCount = transactions.stream()
                .filter(t -> t instanceof UPITransaction)
                .count();

        System.out.println("Total UPI Transaction objects : "+upiCount);

        //Sort and print all transaction class names alphabetically

        System.out.println("Sorted Transaction Class Names:");
        transactions.stream()
                .map(t -> t.getClass().getSimpleName())
                .distinct()
                .sorted()
                .forEach(System.out::println);


        System.out.println("Final Constants");
        System.out.println("Bank Name :"+ Constants.BANK_NAME);
        System.out.println("IFSC Code: " + Constants.IFSC_CODE);

        // Up casting
         // Remember while upcasting new keyword is reuired
        Account acc= new SavingsAccount("SB123", "Ravi Kumar", 20000.0);
        acc.printAccountDetails(); // Works because method is inherited

//      //Downcasting

         // new keyword is not required
        if(acc instanceof SavingsAccount){
            SavingsAccount sa = (SavingsAccount) acc;
            sa.applyCashback("\n Thank you", 5);
        }

    }
}