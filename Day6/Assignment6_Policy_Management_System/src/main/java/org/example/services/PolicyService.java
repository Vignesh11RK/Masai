package org.example.services;

import org.example.models.Customer;
import org.example.models.Policy;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class PolicyService {
    private static volatile PolicyService instance;

    // Stores all customers by ID
    private final Map<String, Customer> customerMap = new ConcurrentHashMap<>();

    // Private constructor to prevent instantiation
    private PolicyService() {}

    // Thread-safe Singleton instance
    public static PolicyService getInstance() {
        if (instance == null) {
            synchronized (PolicyService.class) {
                if (instance == null) {
                    instance = new PolicyService();
                }
            }
        }
        return instance;
    }

    // === Customer Management ===
    public void addCustomer(Customer customer) {
        customerMap.put(customer.getCustomerId(), customer);
    }

    public Customer getCustomer(String customerId) {
        return customerMap.get(customerId);
    }

    public void removeCustomer(String customerId) {
        customerMap.remove(customerId);
    }

    // === Policy Management ===

    public void addPolicy(String customerId, Policy policy) {
        Customer customer = customerMap.get(customerId);
        if (customer != null) {
            customer.getPolicies().put(policy.getPolicyId(), policy);
        } else {
            System.out.println("Customer not found: " + customerId);
        }
    }

    public Policy getPolicy(String customerId, String policyId) {
        Customer customer = customerMap.get(customerId);
        if (customer != null) {
            return customer.getPolicies().get(policyId);
        }
        return null;
    }

    public void removePolicy(String customerId, String policyId) {
        Customer customer = customerMap.get(customerId);
        if (customer != null) {
            customer.getPolicies().remove(policyId);
        }
    }

    // === Maturity Calculation ===

    public double calculateTotalMaturity(String customerId) {
        Customer customer = customerMap.get(customerId);
        if (customer == null)
            return 0;

        return customer.getPolicies().values().stream()
                .mapToDouble(Policy::calculateMaturityAmount)
                .sum();
    }

    // === Display ===

    public void printCustomerDetails(String customerId) {
        Customer customer = customerMap.get(customerId);
        if (customer == null) {
            System.out.println("Customer not found: " + customerId);
            return;
        }

        System.out.println("Customer: " + customer.getName() + " (ID: " + customer.getCustomerId() + ")");
        System.out.println("Age: " + customer.getAge());
        System.out.println("Policies:");

        customer.getPolicies().values().forEach(policy -> {
            System.out.println("  Policy ID: " + policy.getPolicyId() +
                    ", Type: " + policy.getClass().getSimpleName() +
                    ", Premium: " + policy.getPremiumAmount() +
                    ", Term: " + policy.getTermYears() +
                    ", Maturity: " + policy.calculateMaturityAmount());
        });
    }
}


