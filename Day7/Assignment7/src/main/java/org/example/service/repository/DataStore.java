package org.example.service.repository;

import org.example.model.restaurant.BaseDish;
import org.example.model.restaurant.Restaurant;
import org.example.model.users.Customer;
import org.example.model.users.RestaurantOwner;
import org.example.model.users.SystemAdmin;

import java.util.HashMap;
import java.util.Map;

public class DataStore {
    public static Map<String, Customer> customers = new HashMap<>();
    public static Map<String, RestaurantOwner> owners = new HashMap<>();
    public static Map<String, Restaurant> restaurants = new HashMap<>();
    public static SystemAdmin admin;

    public static void initDemoData() {
        admin = new SystemAdmin("admin1", "Admin");

        Restaurant r1 = new Restaurant("r1", "SpiceHub");
        Restaurant r2 = new Restaurant("r2", "TasteTown");

        DataStore.restaurants.put(r1.getId(), r1);
        DataStore.restaurants.put(r2.getId(), r2);

        r1.attachCompetitor(r2);
        r2.attachCompetitor(r1);

        for (int i = 1; i <= 5; i++) {
            r1.addDish(new BaseDish("r1d" + i, "Dish" + i, 100 + i * 10));
            r2.addDish(new BaseDish("r2d" + i, "Meal" + i, 120 + i * 10));
        }

        owners.put("o1", new RestaurantOwner("o1", "Owner1", r1));
        owners.put("o2", new RestaurantOwner("o2", "Owner2", r2));

        customers.put("u1", new Customer("u1", "Alice"));
        customers.put("u2", new Customer("u2", "Bob"));
    }
}
