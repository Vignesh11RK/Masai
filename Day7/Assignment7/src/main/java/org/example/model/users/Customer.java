package org.example.model.users;


import org.example.model.enums.UserRole;
import org.example.model.order.Order;

import java.util.ArrayList;
import java.util.List;

public class Customer extends User {
    private List<Order> orderHistory = new ArrayList<>();

    public Customer(String id, String name) {
        super(id, name, UserRole.CUSTOMER);
    }

    public void addOrder(Order order) {
        orderHistory.add(order);
    }

    public List<Order> getOrderHistory() {
        return orderHistory;
    }
}