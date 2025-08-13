package org.example.model.restaurant;

import java.util.ArrayList;
import java.util.List;

public class BaseDish implements Dish {
    private String id;
    private String name;
    private double price;

    private List<Integer> ratings = new ArrayList<>();
    private int totalOrders = 0;

    public BaseDish(String id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    @Override public String getId() { return id; }
    @Override public String getName() { return name; }
    @Override public double getPrice() { return price; }
    @Override public void setPrice(double price) { this.price = price; }

    @Override public void addRating(int rating) { ratings.add(rating); }
    @Override public double getAverageRating() {
        return ratings.stream().mapToInt(r -> r).average().orElse(0.0);
    }
    @Override public int getTotalRatings() { return ratings.size(); }

    @Override public int getTotalOrders() { return totalOrders; }
    @Override public void incrementOrders() { totalOrders++; }
}
