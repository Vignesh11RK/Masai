package org.example.service.strategy;


import org.example.model.order.Order;

public class NoDiscount implements DiscountStrategy {
    @Override public double applyDiscount(Order order) { return 0; }
}


