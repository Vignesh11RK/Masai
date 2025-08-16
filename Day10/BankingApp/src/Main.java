import Entities.Account;
import Entities.Customer;
import Entities.Transaction;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

        private static final Map<String, Customer> customers=new HashMap<>();
        private static final Map<String, Account> accounts=new HashMap<>();
        private static final List<Transaction> transactions=new ArrayList<>();

        private static final DateTimeFormatter dateformatter=DateTimeFormatter.ofPattern("yyyy-MM-dd");
        private static final DateTimeFormatter timeformatter=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:MM:SS");


        private static final Scanner scanner=new Scanner(System.in);

        public static void main(String[] args){
            System.out.println("Welcome to HDFC Bank App");

            try{
                while(true){
                    showMainMenu();
                }
            }catch (Exception e){
                System.out.println("Error: "+e.getMessage());

            }finally {
                scanner.close();
            }

        }

        private static void showMainMenu(){
            System.out.println("==Main Menu==");
            System.out.println("1.Register new CUSTOMER");
            System.out.println("2.CREATE NEW ACCOUNT");
            System.out.println("3.PERFORMANCE TRANSACTION");
            System.out.println("4.VIEW ACCOUNT DETAILS");

            System.out.println("5.VIEW TRANSACTION HISTORY");
            System.out.println("5.EXIT");
            System.out.println("ENTER UR CHOICE");

            int choice=getInput();
            switch (choice){
                case 1:
                    registerCustomer();
                    break;
                case 2:
            }

        }

        private static int getInput(){
            while(true){
                try {
                    return Integer.parseInt(scanner.nextLine().trim());
                }

                catch (NumberFormatException e){
                    System.out.println("Please enter the valid number :");
                }
            }
        }

     // we could use regular expressin or multithreading

        public static void registerCustomer(){
            System.out.println("\n==CUSTOMER REGISTRATION==");
            System.out.println("ENTER CUSTOMER ID");

            String customerId=scanner.nextLine().trim();


            if(customers.containsKey(customerId)){
                System.out.println("Customer already Exist");
                return;
            }

            System.out.println("Enter name : ");
            String name =scanner.nextLine().trim();

            System.out.println("Enter phone : ");
            String phone =scanner.nextLine().trim();

            System.out.println("Enter email : ");
            String email =scanner.nextLine().trim();

            System.out.println("Enter dob in yyyy-mm-dd");
            String dobStr=scanner.nextLine().trim();
            LocalDate dateOfBirth;
            try{
                dateOfBirth=LocalDate.parse(dobStr,dateformatter);

            }catch (Exception e){
                System.out.println("Invalid Date format");
                return;
            }

            System.out.println("Enter password");
            String password=scanner.nextLine().trim();

            Customer customer=new Customer(customerId,name,email,phone,password,dateOfBirth);
            customers.put(customerId,customer);
            System.out.println("Customer registered successfully");


        }






}
