package org.example.models;
import java.util.HashMap;
import java.util.Map;


public class Customer {
    private String customerId;
    private String name;
    private int age;
    private Map<String, Policy> policies;

    //constructor

    public Customer(String customerId, String name, int age) {
        this.customerId = customerId;
        this.name = name;
        this.age = age;
        this.policies = new HashMap<>();
    }

    public void addPolicy(Policy policy) {
        policies.put(policy.getPolicyId(), policy);
    }

    //getters

    public Map<String, Policy> getPolicies() {
        return policies;
    }

    public String getCustomerId() { return customerId; }
    public String getName() { return name; }
    public int getAge() { return age; }


}
