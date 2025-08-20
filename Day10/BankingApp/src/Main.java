import Entities.*;
import Exceptions.InsufficientBalance;
import enums.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    private static final Map<String, Customer> customers = new HashMap<>();
    private static final Map<String, Account> accounts = new HashMap<>();
    private static final List<Transaction> transactions = new ArrayList<>();
    private static final Scanner sc = new Scanner(System.in);


    private static final DateTimeFormatter dateformatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter timeformatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:MM:SS");


    private static final Scanner scanner = new Scanner(System.in);



    public static void main(String[] args) {
        System.out.println("Welcome to HDFC Bank App");

        try {
            while (true) {
                showMainMenu();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());

        } finally {
            scanner.close();
        }

    }

    private static void showMainMenu() {
        System.out.println("==Main Menu==");
        System.out.println("1.Register new CUSTOMER");
        System.out.println("2.CREATE NEW ACCOUNT");
        System.out.println("3.PERFORMANCE TRANSACTION");
        System.out.println("4.VIEW ACCOUNT DETAILS");

        System.out.println("5.VIEW TRANSACTION HISTORY");
        System.out.println("6.EXIT");
//        System.out.println("7. Demo Mode (Complete Flow)");

        System.out.println("ENTER UR CHOICE");

        int choice = getInput();
        switch (choice) {
            case 1:
                registerCustomer();
                break;

            case 2:
                createAccount();
                break;

            case 3:
                performTransaction();
                break;

            case 4:
                viewAccountDetails();
                break;

            case 5:
                viewTransactionHistory();  // chechk
                break;
            case 6:
                System.out.println("Exiting... Thank you!");
                System.exit(0); // or return if inside loop
                break;

//            case 7:
//                runDemoMode();
//                break;

            default:
                System.out.println("Invalid choice! Please select a valid option.");
                break;

        }


    }

//    private static void runDemoMode() {
//    }


    private static int getInput() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter the valid number :");
            }
        }
    }

    // we could use regular expressin or multithreading

    public static void registerCustomer() {
        System.out.println("\n==CUSTOMER REGISTRATION==");
        System.out.println("ENTER CUSTOMER ID");

        String customerId = scanner.nextLine().trim();


        if (customers.containsKey(customerId)) {
            System.out.println("Customer already Exist");
            return;
        }

        System.out.println("Enter name : ");
        String name = scanner.nextLine().trim();

        System.out.println("Enter phone : ");
        String phone = scanner.nextLine().trim();
        String phoneRegex = "^(?:\\+91|91|0)?[6-9]\\d{9}$";
        Pattern patternp=Pattern.compile(phoneRegex);
        Matcher matcherp=patternp.matcher(phone);


        if (matcherp.matches()){
            System.out.println("Valid Phone no");
        }else{
            System.out.println("Invalid phone no");
            return;
        }




        System.out.println("Enter email : ");
        String email = scanner.nextLine().trim();
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

        Pattern pattern=Pattern.compile(emailRegex);
        Matcher matcher=pattern.matcher(email);

        if(matcher.matches()){
            System.out.println("Valid email");
        }else {
            System.out.println("Invalid email");
            return;
        }



        System.out.println("Enter dob in yyyy-mm-dd");
        String dobStr = scanner.nextLine().trim();
        LocalDate dateOfBirth;
        try {
            dateOfBirth = LocalDate.parse(dobStr, dateformatter);

        } catch (Exception e) {
            System.out.println("Invalid Date format");
            return;
        }

        System.out.println("Enter password");
        String password = scanner.nextLine().trim();

        Customer customer = new Customer(customerId, name, email, phone, password, dateOfBirth);
        customers.put(customerId, customer);
        System.out.println("Customer registered successfully");

    }


//    private static void createAccount() {
//        System.out.println("\n ==Create new Account==");
//        System.out.println("Please enter cutomer Id");
//        String cutomerId = scanner.nextLine().trim();
//
//        Customer customer = customers.get(cutomerId);
//        if (customer == null) {
//            System.out.println("Customer not found");
//            return;
//        }
//
//        System.out.println("Choose account type ");
//        System.out.println("1) Savings Account (6% Interest rate and min balance of 10,000");
//        System.out.println("2)Current Account (6 % Interest rate and no min balance) ");
//        System.out.println("Select Account type");
//
//        int typeChoice = getInput();
//
//        Account account = new Account() {
//            @Override
//            public BigDecimal getInterestRate() {
//                return null;
//            }
//
//            @Override
//            public BigDecimal getMinimumBalance() {
//                return null;
//            }
//        };
//        account.setBalance(account.getBalance()); // assuming you have a setter
//
//        System.out.println("Enter initial balance: ");
//        String balance = scanner.nextLine().trim();
//
//        try {
//            BigDecimal initialBalance = new BigDecimal(balance);
//            String accountNo = account.getAccountNo();
//
//            switch (typeChoice) {
//                case 1:
//                    account = new SavingsAccount(accountNo, cutomerId, initialBalance);
//                    break;
//                case 2:
//                    account = new CurrentAccount(accountNo, cutomerId, initialBalance);
//                    break;
//
//                default:
//                    System.out.println("Invalid account type");
//                    return;
//            }
//
//            accounts.put(accountNo, account);
//            System.out.println("Congrats account created successfully"+account.getAccountNo());
//
//        } catch (NumberFormatException e) {
//            System.out.println("Invalid balance amount !");
//        }
//
//    }
//


    private static void createAccount() {
        System.out.println("\n== Create New Account ==");
        System.out.println("Please enter customer ID:");
        String customerId = scanner.nextLine().trim();

        Customer customer = customers.get(customerId);
        if (customer == null) {
            System.out.println("Customer not found.");
            return;
        }

        System.out.println("Choose account type:");
        System.out.println("1) Savings Account (6% Interest rate and min balance of 10,000)");
        System.out.println("2) Current Account (6% Interest rate and no min balance)");
        System.out.print("Select Account Type: ");
        int typeChoice = getInput();

        System.out.print("Enter initial balance: ");
        String balanceInput = scanner.nextLine().trim();

        try {
            BigDecimal initialBalance = new BigDecimal(balanceInput);

            // Generate account number (can be improved)
            String accountNo = "ACC" + (accounts.size() + 1001);

            Account account;

            switch (typeChoice) {
                case 1:
                    account = new SavingsAccount(accountNo, customerId, initialBalance);
                    break;
                case 2:
                    account = new CurrentAccount(accountNo, customerId, initialBalance);
                    break;
                default:
                    System.out.println("Invalid account type selected.");
                    return;
            }

            accounts.put(accountNo, account); // Save to map
            System.out.println("Congrats! Account created successfully.");
            System.out.println("Your Account Number is: " + account.getAccountNo());
        }

        catch (NumberFormatException e) {
            System.out.println("Invalid balance amount!");

        }
    }




    private static String generateAccountNo() {
        return String.format("%10d", System.currentTimeMillis());
    }

    private static void performTransacttion() {
        System.out.println("\n ==Perfom Transaction==");

        System.out.println("1) Deposit");
        System.out.println("2) Withdraw");
        System.out.println("Please select transaction type");

        int transactionChoice = getInput();

        switch (transactionChoice) {
            case 1:
                performDeposit();
            case 2:
                performWithdraw();
            case 3:
                performWithdraw();
            default:
                System.out.println("Invalid transaction type");
        }
    }

    private static void performTransaction() {
        System.out.println("\n=====Perform Transaction===");
        System.out.println("1. Deposit");
        System.out.println("2. Withdraw");
        System.out.println("3. Transfer");

        System.out.println("Select Transaction type");
        int transactionChoice = getInput();

        switch (transactionChoice) {
            case 1:
                performDeposit();
                break;
            case 2:
                performWithdraw();
                break;
            case 3:
                performTransfer();
                break;
        }
    }


    private static void performDeposit() {
        System.out.println("enter account no");
        String accountNo = scanner.nextLine().trim();

        Account account = accounts.get(accountNo);
        if (account == null) {
            System.out.println("Account not found");
            return;
        }

        System.out.println("Enter deposit amount");
        String amountStr = scanner.nextLine().trim();


        try {
            BigDecimal amount = new BigDecimal(amountStr);
            account.deposit(amount);
            String transactionsId = generateTransactionId(); //
//            Transaction transaction=new Transaction(transactionsId,accountNo,TransactionType.DEPOSIT,amount, LocalDateTime.now());
            Transaction transaction = new Transaction(accountNo, amount, LocalDateTime.now(), transactionsId, TransactionType.DEPOSIT);
            transaction.add(transaction);
            System.out.println("Deposit Done successfully! New Balance: " + account.getBalance());
        } catch (NumberFormatException e) {
            System.out.println("Invalid Amount");
        }
    }

    private static String generateTransactionId() {
        return "HDFC_TXN" + System.currentTimeMillis();
    }

    private static void performWithdraw() {
        System.out.println("Enter the Account no");
        String accountNo = scanner.nextLine().trim();
        Account account = accounts.get(accountNo);
        if (account == null) {
            System.out.println("Account not registered");
            return;
        }
        System.out.println("Enter withdrawal amount");
        String amountStr = scanner.nextLine().trim();  // sc should have been there
        try {
            BigDecimal amount = new BigDecimal(amountStr);
            account.withdraw(amount);
            String transactionId = generateTransactionId();

            Transaction transaction = new Transaction(accountNo, amount, LocalDateTime.now(), transactionId, TransactionType.WITHDRAW);
            transactions.add(transaction);
            System.out.println("Withdrawal Successfull" + account.getBalance());

        } catch (NumberFormatException e) {
            System.out.println("Invalid Amount");
        } catch (InsufficientBalance e) {
            System.out.println("Error " + e.getMessage());
        }


    }


    private static void performTransfer() {
        System.out.println("Enter Source account no: ");
        String fromAccountNo = sc.nextLine().trim();
        Account fromAccount = accounts.get(fromAccountNo);
        if (fromAccount == null) {
            System.out.println("Source Account not found");
            return;
        }

        System.out.println("Enter Destination account no: ");
        String toAccountNoStr = sc.nextLine().trim();
        Account toAccount = accounts.get(toAccountNoStr);
        if (toAccount == null) {
            System.out.println("Destination Account not found");
            return;
        }


        if (fromAccountNo.equals(toAccountNoStr)) {
            System.out.println("cant transfer to same account");
            return;
        }

        System.out.println("Enter amount");

        String amountStr = sc.nextLine().trim();

        try {
            BigDecimal amount = new BigDecimal(amountStr);
            // Withdraw and deposit
            fromAccount.withdraw(amount);
            toAccount.deposit(amount);


            // Creation of  transaction object
            String transactionId = generateTransactionId();
            Transaction transaction = new Transaction(fromAccountNo, amount, LocalDateTime.now(), transactionId, TransactionType.TRANSFER, toAccountNoStr);

            transactions.add(transaction);   //Add transaction to global list
            System.out.println("Transfer successful!");



        } catch (NumberFormatException e) {
            System.out.println("Invalid Amount entered");
        } catch (InsufficientBalance e) {
            System.out.println("Error " + e.getMessage());
        }
    }


    private static void viewAccountDetails() {
        System.out.println("\n===Account Details===");
        System.out.println("enter account number");

        String accountNoStr = sc.nextLine().trim();
        Account account = accounts.get(accountNoStr);

        if (account == null) {
            System.out.println("Account not found");
            return;
        }

        System.out.println(account.toString());

        if (account instanceof SavingsAccount) {
            System.out.println("Account Type: Savings Account");
        } else {
            System.out.println("Account Type: Currents Accont");
        }

    }


    private static void viewTransactionHistory() {
        System.out.println("\n=== Transaction History ===");
        System.out.println("Enter Account Number:");

        String accountNo = sc.nextLine().trim();

        Account account = accounts.get(accountNo);

        if (account == null) {
            System.out.println("Account not found.");
            return;
        }
// putting the transactions in the stream
        List<Transaction> accountTransactions = transactions.stream()
                .filter(t -> t.getAccountNo().equals(accountNo) || (t.getToAccountNo() !=null && t.getToAccountNo().equals(accountNo)))
                .sorted(Comparator.comparing(Transaction::getTimestamp).reversed())
                .toList();

        if (accountTransactions.isEmpty()) {
            System.out.println("No transactions found.");
            return;
        }

        System.out.println("\n--- Transaction List ---");
        for (Transaction transaction : accountTransactions) {

            System.out.println(transaction); // Ensure Transaction has a meaningful toString()
        }

        Map<TransactionType, Long> transactionSummary = accountTransactions.stream()
                .collect(Collectors.groupingBy(Transaction::getType, Collectors.counting()));

        System.out.println("\n=== Transaction Summary ===");
        transactionSummary.forEach((type, count) -> {
            System.out.println(type.getDisplayName() + ": " + count);
        });
    }


//    private static void runDemoMode() {
//        System.out.println("\n=== Demo Mode - Complete Banking Flow ===");
//
//        // Step 1: Register demo customers
//        Customer demo1 = new Customer("CUST1001", "Alice", "alice@example.com");
//        Customer demo2 = new Customer("CUST1002", "Bob", "bob@example.com");
//        customers.put(demo1.getCustomerID(), demo1);
//        customers.put(demo2.getCustomerID(), demo2);
//        System.out.println("✓ Customers registered successfully");
//
//        // Step 2: Create demo accounts
//        Account savings = new SavingsAccount("ACC1001", demo1.getCustomerID(), new BigDecimal("5000"));
//        Account current = new CurrentAccount("ACC1002", demo2.getCustomerID(), new BigDecimal("2000"));
//        accounts.put(savings.getAccountNo(), savings);
//        accounts.put(current.getAccountNo(), current);
//        System.out.println("✓ Accounts created successfully");
//
//        //   Step 3: Perform transactions
//        savings.deposit(new BigDecimal("1000"));
//        transactions.add(new Transaction(savings.getAccountNo(), TransactionType.DEPOSIT, new BigDecimal("1000")));
//        System.out.println("✓ Deposit successful");
//
//        current.withdraw(new BigDecimal("500"));
//        transactions.add(new Transaction(current.getAccountNo(), TransactionType.WITHDRAW, new BigDecimal("500")));
//        System.out.println("✓ Withdrawal successful");
//
//        savings.transfer(current, new BigDecimal("800"));
//        transactions.add(new Transaction(savings.getAccountNo(), TransactionType.TRANSFER, new BigDecimal("800")));
//        System.out.println("✓ Transfer successful");
//
//        // Step 4: Show balances + interest
//        System.out.println("\n4. Account details:");
//        System.out.println("Savings Account: " + savings.getBalance() +
//                " (Interest: " + savings.calculateInterest() + ")");
//        System.out.println("Current Account: " + current.getBalance() +
//                " (Interest: " + current.calculateInterest() + ")");
//
//        // Step 5: Stream-based transaction history
//        System.out.println("\n5. Transaction history (using Java 8 Streams):");
//        transactions.stream()
//                .filter(tx -> tx.getAccountNo().equals(savings.getAccountNo()))
//                .sorted(Comparator.comparing(Transaction::getTimestamp))
//                .forEach(tx -> System.out.println("  " + tx.getType() + " - " + tx.getAmount() + " at " + tx.getTimestamp()));
//
//        // Step 6: Polymorphism demo
//        System.out.println("\n6. Demonstrating polymorphism:");
//        List<Account> demoAccounts = Arrays.asList(savings, current);
//        for (Account acc : demoAccounts) {
//            System.out.println("  " + acc.getClass().getSimpleName()
//                    + " - Min Balance: " + acc.getMinimumBalance()
//                    + ", Interest Rate: " + acc.getInterestRate() + "%");
//        }
//
//
//        System.out.println("\n=== Demo completed successfully! ===");
//        System.out.println("All Java concepts demonstrated: OOP, Inheritance, Polymorphism,");
//        System.out.println("Collections, Streams, Lambdas, Exception Handling.");
//    }

}









