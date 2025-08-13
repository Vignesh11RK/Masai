package org.example.service.strategy;


import org.example.model.order.Order;

public interface DiscountStrategy {
    double applyDiscount(Order order);
}

