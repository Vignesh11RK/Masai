package org.example.model.order;


import org.example.model.restaurant.Dish;
import org.example.service.strategy.DiscountStrategy;

import java.util.List;

public class Order {
    private List<Dish> dishes;
    private DiscountStrategy discountStrategy;
    private OrderStatus status;
    private static final double GST = 0.05;

    public Order(List<Dish> dishes, DiscountStrategy discountStrategy) {
        this.dishes = dishes;
        this.discountStrategy = discountStrategy;
        this.status = OrderStatus.PLACED;
    }

    public double getBaseAmount() {
        return dishes.stream().mapToDouble(Dish::getPrice).sum();
    }

    public double getGST() {
        return getBaseAmount() * GST;
    }

    public double getDiscount() {
        return discountStrategy.applyDiscount(this);
    }

    public double getTotal() {
        return getBaseAmount() + getGST() - getDiscount();
    }

    public List<Dish> getDishes() { return dishes; }
    public OrderStatus getStatus() { return status; }
}