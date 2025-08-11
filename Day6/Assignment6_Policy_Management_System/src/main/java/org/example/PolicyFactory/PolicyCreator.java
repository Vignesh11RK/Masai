package org.example.PolicyFactory;

import org.example.models.Policy;

public interface PolicyCreator {
    Policy create(String id, double premium, int term);
}
