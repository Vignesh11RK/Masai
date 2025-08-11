package org.example;
import org.example.PolicyFactory.PolicyFactory;
import org.example.models.Customer;
import org.example.models.HealthInsurance;
import org.example.models.LifeInsurancePolicy;
import org.example.models.Policy;
import org.example.services.PolicyService;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        PolicyService service = PolicyService.getInstance();

        // === Register policy types with the factory ===
        PolicyFactory.registerPolicyType("LIFE", (id, premium, term) -> new LifeInsurancePolicy(id, premium, term));
        PolicyFactory.registerPolicyType("HEALTH", (id, premium, term) -> new HealthInsurance(id, premium, term));

        // === Add Customers ===
        Customer c1 = new Customer("C001", "Alice", 30);
        Customer c2 = new Customer("C002", "Bob", 45);
        Customer c3 = new Customer("C003", "Charlie", 28);

        service.addCustomer(c1);
        service.addCustomer(c2);
        service.addCustomer(c3);

        // === Add Policies via Factory ===
        service.addPolicy("C001", PolicyFactory.createPolicy("LIFE", "P001", 5000, 10));
        service.addPolicy("C001", PolicyFactory.createPolicy("HEALTH", "P002", 3000, 5));
        service.addPolicy("C002", PolicyFactory.createPolicy("LIFE", "P003", 7000, 15));
        service.addPolicy("C003", PolicyFactory.createPolicy("HEALTH", "P004", 2000, 7));

        // === List All Customers and Their Policies ===
        System.out.println("\n--- All Customers and Policies ---");
        printAllCustomers(service);

        // === Calculate Total Maturity for a Customer ===
        System.out.println("\n--- Total Maturity for C001 ---");
        double maturity = service.calculateTotalMaturity("C001");
        System.out.println("Total Maturity Amount: " + maturity);

        // === Remove a Policy from a Customer ===
        System.out.println("\n--- Removing Policy P002 from C001 ---");
        service.removePolicy("C001", "P002");
        service.printCustomerDetails("C001");

        // === Remove a Customer ===
        System.out.println("\n--- Removing Customer C003 ---");
        service.removeCustomer("C003");

        // === Display Customers Sorted by Name (TreeMap) ===
        System.out.println("\n--- Customers Sorted by Name ---");
        TreeMap<String, Customer> sortedByName = new TreeMap<>();
        for (Customer cust : getAllCustomers(service)) {
            sortedByName.put(cust.getName(), cust);
        }
        for (Map.Entry<String, Customer> entry : sortedByName.entrySet()) {
            System.out.println("Name: " + entry.getKey() + ", ID: " + entry.getValue().getCustomerId());
        }

        // === Display Policies Sorted by Premium Amount (Custom Comparator) ===
        System.out.println("\n--- All Policies Sorted by Premium Amount ---");
        List<Policy> allPolicies = getAllCustomers(service).stream()
                .flatMap(cust -> cust.getPolicies().values().stream())
                .sorted(Comparator.comparingDouble(Policy::getPremiumAmount))
                .collect(Collectors.toList());

        for (Policy policy : allPolicies) {
            System.out.println("Policy ID: " + policy.getPolicyId() +
                    ", Premium: " + policy.getPremiumAmount());
        }

        // === Iterate over Policies Using keySet() ===
        System.out.println("\n--- Iterate Using keySet() ---");
        Customer customer = service.getCustomer("C001");
        if (customer != null) {
            for (String key : customer.getPolicies().keySet()) {
                System.out.println("Policy ID: " + key);
            }
        }

        // === Iterate over Policies Using entrySet() ===
        System.out.println("\n--- Iterate Using entrySet() ---");
        if (customer != null) {
            for (Map.Entry<String, Policy> entry : customer.getPolicies().entrySet()) {
                System.out.println("Policy ID: " + entry.getKey() +
                        ", Premium: " + entry.getValue().getPremiumAmount());
            }
        }

        // === Iterate over Policies Using forEach() ===
        System.out.println("\n--- Iterate Using forEach() ---");
        if (customer != null) {
            customer.getPolicies().forEach((id, pol) ->
                    System.out.println("Policy ID: " + id + ", Term: " + pol.getTermYears()));
        }
    }

    // Helper to print all customers
    private static void printAllCustomers(PolicyService service) {
        for (Customer cust : getAllCustomers(service)) {
            service.printCustomerDetails(cust.getCustomerId());
        }
    }

    // Helper to retrieve all customers using reflection (since customerMap is private)
    private static Collection<Customer> getAllCustomers(PolicyService service) {
        try {
            java.lang.reflect.Field field = PolicyService.class.getDeclaredField("customerMap");
            field.setAccessible(true);
            Map<String, Customer> map = (Map<String, Customer>) field.get(service);
            return map.values();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
