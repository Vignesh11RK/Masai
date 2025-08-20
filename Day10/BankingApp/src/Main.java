import Entities.*;
import Exceptions.InsufficientBalance;
import enums.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

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
        System.out.println("5.EXIT");
        System.out.println("ENTER UR CHOICE");

        int choice = getInput();
        switch (choice) {
            case 1:
                registerCustomer();
                break;
            case 2:

//                case 3:
//                    performTransaction();

        }

    }

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

        System.out.println("Enter email : ");
        String email = scanner.nextLine().trim();

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


    private static void createAccount() {
        System.out.println("\n ==Create new Account==");
        System.out.println("Please enter cutomer Id");
        String cutomerId = scanner.nextLine().trim();

        Customer customer = customers.get(cutomerId);
        if (customer == null) {
            System.out.println("Customer not found");
            return;
        }

        System.out.println("Choose account type ");
        System.out.println("1) Savings Account (6% Interest rate and min balance of 10,000");
        System.out.println("2)Current Account (6 % Interest rate and no min balance) ");
        System.out.println("Select Account type");

        int typeChoice = getInput();
        Account account = null;

        System.out.println("Enter initial balance: ");
        String balance = scanner.nextLine().trim();

        try {
            BigDecimal initialBalance = new BigDecimal(balance);
            String accountNo = account.getAccountNo();

            switch (typeChoice) {
                case 1:
                    account = new SavingsAccount(accountNo, cutomerId, initialBalance);
                    break;
                case 2:
                    account = new CurrentAccount(accountNo, cutomerId, initialBalance);
                    break;

                default:
                    System.out.println("Invalid account type");
                    return;
            }

            accounts.put(accountNo, account);
            System.out.println("Congrats account created successfully");

        } catch (NumberFormatException e) {
            System.out.println("Invalid balance amount !");
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

    private static void performWithdraw(){
        System.out.println("Enter the Account no");
        String accountNo= scanner.nextLine().trim();
        Account account =accounts.get(accountNo);
        if(account ==null){
            System.out.println("Account not registered");
            return;
        }
        System.out.println("Enter withdrawal amount");
        String amountStr=scanner.nextLine().trim();  // sc should have been there
        try {
            BigDecimal amount = new BigDecimal(amountStr);
            account.withdraw(amount);
            String transactionId = generateTransactionId();

            Transaction transaction = new Transaction(accountNo,amount, LocalDateTime.now(),transactionId, TransactionType.WITHDRAW);
            transactions.add(transaction);
            System.out.println("Withdrawal Successfull"+ account.getBalance());

        }catch (NumberFormatException e)
        {
            System.out.println("Invalid Amount");
        }
        catch (InsufficientBalance e){
            System.out.println("Error "+e.getMessage());
        }


    }


    private static void performTransfer(){
        System.out.println("Enter Source account no: ");
        String fromAccountNo = sc.nextLine().trim();
        Account fromAccount = accounts.get(fromAccountNo);
        if (fromAccount==null){
            System.out.println("Source Account not found");
            return;
        }

        System.out.println("Enter Destination account no: ");
        String toAccountNoStr = sc.nextLine().trim();
        Account toAccount = accounts.get(toAccountNoStr);
        if (toAccount==null){
            System.out.println("Destination Account not found");
            return;
        }


        if (fromAccountNo.equals(toAccountNoStr))
        {
            System.out.println("cant transfer to same account");
        }

        System.out.println("Enter amount");

        String amountStr = sc.nextLine().trim();

        try {
            BigDecimal amount = new BigDecimal(amountStr);
            fromAccount.withdraw(amount);
            toAccount.deposit(amount);
            String transactionId = generateTransactionId();
            Transaction transaction = new Transaction(fromAccountNo,amount, LocalDateTime.now(),transactionId, TransactionType.WITHDRAW,toAccountNoStr);
        }catch (NumberFormatException e)
        {
            System.out.println("invalid Amount entered");
        }catch (InsufficientBalance e)
        {
            System.out.println("Error "+e.getMessage());
        }
    }



    private static void viewAccountDetails(){
        System.out.println("\n===Account Details===");
        System.out.println("enter account number");

        String accountNoStr = sc.nextLine().trim();
        Account account = accounts.get(accountNoStr);

        if (account==null){
            System.out.println("Account not found");
            return;
        }

        System.out.println(account.toString());

        if(account instanceof SavingsAccount)
        {
            System.out.println("Account Type: Savings Account");
        }
        else {
            System.out.println("Account Type: Currents Accont");
        }

    }


    private static void viewTransactionHistory(){
        System.out.println("\n===Transaction History===");
        System.out.println("Konsa Account ka Transaction Dekhna hai");

        String AccountNo = sc.nextLine().trim();
        Account account = accounts.get(AccountNo);

        if (account==null){
            System.out.println("Account not found");
            return;
        }
        List<Transaction> accountTransaction = transactions.stream()
                .filter(t->t.getAccountNo().equals(AccountNo))
                .sorted(Comparator.comparing(Transaction::getTimestamp).reversed())
                .toList();

        if (accountTransaction.isEmpty())
        {
            System.out.println("No transactions found");
            return;
        }

        for (Transaction transaction:accountTransaction)
        {
            System.out.println(transaction.toString());
        }

        Map<TransactionType,Long> transactionSummary = accountTransaction.stream()
                .collect(Collectors.groupingBy(Transaction::getType, Collectors.counting()));

        System.out.println("===Transaction Summary===");
        transactionSummary.forEach((type,count)->
        {
            System.out.println(type.getDisplayName()+" "+count);
        });

    }


}

