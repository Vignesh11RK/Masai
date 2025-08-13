package org.example.model.users;

import org.example.model.enums.UserRole;
import org.example.model.restaurant.Restaurant;

public class RestaurantOwner extends User {
    private Restaurant restaurant;

    public RestaurantOwner(String id, String name, Restaurant restaurant) {
        super(id, name, UserRole.RESTAURANT_OWNER);
        this.restaurant = restaurant;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }
}


