package org.example.model.restaurant;

public interface Dish {
    String getId();
    String getName();
    double getPrice();
    void setPrice(double price);
    void addRating(int rating);
    double getAverageRating();
    int getTotalRatings();
    int getTotalOrders();
    void incrementOrders();
}
