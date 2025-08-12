package org.example.service.strategy;

import org.example.model.order.Order;



public class PercentageDiscount implements DiscountStrategy {
    private double rate;

    public PercentageDiscount(double rate) { this.rate = rate; }

    @Override
    public double applyDiscount(Order order) {
        return order.getBaseAmount() * rate;
    }
}
