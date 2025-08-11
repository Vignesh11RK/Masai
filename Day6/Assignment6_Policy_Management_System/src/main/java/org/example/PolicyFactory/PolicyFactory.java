package org.example.PolicyFactory;

import org.example.models.Policy;

import java.util.HashMap;
import java.util.Map;

public class PolicyFactory {

    private static final Map<String, PolicyCreator> registry = new HashMap<>();
    // Register a new policy type dynamically
    public static void registerPolicyType(String type, PolicyCreator creator) {
        registry.put(type.toUpperCase(), creator);
    }

    // Main method to create policies
    public static Policy createPolicy(String type, String policyId, double premium, int termYears) {
        PolicyCreator creator = registry.get(type.toUpperCase());
        if (creator == null) {
            throw new IllegalArgumentException("Unknown policy type: " + type);
        }
        return creator.create(policyId, premium, termYears);
       }
    }
