package org.example.app;

import org.example.model.order.Order;
import org.example.model.restaurant.*;
import org.example.service.repository.DataStore;
import org.example.service.strategy.NoDiscount;
import org.example.service.strategy.PercentageDiscount;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ChefWarApp {
    public static void main(String[] args) {
        DataStore.initDemoData();

        while (true) {
            System.out.println("\n--- Login ---");
            System.out.println("1. Customer");
            System.out.println("2. Restaurant Owner");
            System.out.println("3. System Admin");
            System.out.println("0. Exit");
            int role = InputUtil.readInt("Select role: ", 0, 3);

            switch (role) {
                case 1 -> handleCustomer();
                case 2 -> handleOwner();
                case 3 -> handleAdmin();
                case 0 -> {
                    System.out.println("Goodbye!");
                    return;
                }
            }
        }
    }

    private static void handleCustomer() {
        String uid = InputUtil.readLine("Enter Customer ID (u1/u2): ");
        Customer c = DataStore.customers.get(uid);
        if (c == null) { System.out.println("Invalid."); return; }

        while (true) {
            System.out.println("\n--- Customer Menu ---");
            System.out.println("1. Browse Restaurants");
            System.out.println("2. View Order History");
            System.out.println("0. Logout");
            int ch = InputUtil.readInt("Choose: ", 0, 2);

            if (ch == 0) return;
            if (ch == 2) {
                c.getOrderHistory().forEach(o ->
                        System.out.printf("Order: ₹%.2f (%d items)%n", o.getTotal(), o.getDishes().size()));
                continue;
            }

            System.out.println("\nRestaurants:");
            DataStore.restaurants.values().stream()
                    .filter(r -> !r.isBlocked())
                    .forEach(r -> System.out.printf("%s (%s)%n", r.getId(), r.getName()));

            String rid = InputUtil.readLine("Enter Restaurant ID: ");
            Restaurant r = DataStore.restaurants.get(rid);
            if (r == null || r.isBlocked()) {
                System.out.println("Invalid/Blocked.");
                continue;
            }

            List<Dish> menu = r.getMenu().stream().collect(Collectors.toList());
            menu.forEach(d -> System.out.printf("%s: %s (₹%.2f)%n", d.getId(), d.getName(), d.getPrice()));

            String did = InputUtil.readLine("Enter Dish ID: ");
            r.findDish(did).ifPresentOrElse(d -> {
                Dish selected = d;
                while (true) {
                    System.out.println("Add-ons: 1)Cheese 2)Chicken 3)Sauce 0)Done");
                    int a = InputUtil.readInt("Choice: ", 0, 3);
                    if (a == 1) selected = new ExtraCheese(selected);
                    else if (a == 2) selected = new DoubleChicken(selected);
                    else if (a == 3) selected = new SpicySauce(selected);
                    else break;
                }

                // Rate before ordering
                int stars = InputUtil.readInt("Rate dish now (1-5) or 0 to skip: ", 0, 5);
                if (stars > 0) d.addRating(stars);

                boolean hasOffer = InputUtil.readInt("Apply 10% offer? 1 Yes / 0 No: ", 0, 1) == 1;
                var strategy = hasOffer ? new PercentageDiscount(0.10) : new NoDiscount();
                Order o = new Order(List.of(selected), strategy);

                r.recordSale(d.getId(), o.getTotal());
                c.addOrder(o);
                System.out.printf("Order placed! Total: ₹%.2f%n", o.getTotal());

            }, () -> System.out.println("Invalid Dish."));
        }
    }

    private static void handleOwner() {
        String oid = InputUtil.readLine("Enter Owner ID (o1/o2): ");
        RestaurantOwner owner = DataStore.owners.get(oid);
        if (owner == null) { System.out.println("Invalid."); return; }

        Restaurant r = owner.getRestaurant();

        while (true) {
            System.out.println("\n--- Owner Menu (" + r.getName() + ") ---");
            System.out.println("1. Add Dish");
            System.out.println("2. Change Price");
            System.out.println("3. Back");
            int c = InputUtil.readInt("Choose: ", 1, 3);

            if (c == 3) return;

            if (c == 1) {
                String nid = InputUtil.readLine("New Dish ID: ");
                String nm = InputUtil.readLine("Dish Name: ");
                double pr = Double.parseDouble(InputUtil.readLine("Price: "));
                r.addDish(new BaseDish(nid, nm, pr));
            } else {
                r.getMenu().forEach(d -> System.out.printf("%s: ₹%.2f%n", d.getId(), d.getPrice()));
                String did = InputUtil.readLine("Dish ID to change: ");
                double pr = Double.parseDouble(InputUtil.readLine("New Price: "));
                r.setPrice(did, pr);
                System.out.println("Price updated with competition effect.");
            }
        }
    }

    private static void handleAdmin() {
        SystemAdmin admin = DataStore.admin;

        while (true) {
            System.out.println("\n--- Admin Menu ---");
            System.out.println("1. View Analytics");
            System.out.println("2. Block/Unblock Restaurant");
            System.out.println("0. Logout");
            int c = InputUtil.readInt("Choose: ", 0, 2);

            if (c == 0) return;

            if (c == 2) {
                DataStore.restaurants.values().forEach(r ->
                        System.out.printf("%s (%s) - %s%n", r.getId(), r.getName(),
                                r.isBlocked() ? "Blocked" : "Active"));
                String rid = InputUtil.readLine("Enter Restaurant ID: ");
                Restaurant r = DataStore.restaurants.get(rid);
                if (r != null) {
                    if (r.isBlocked()) {
                        System.out.println("Currently blocked—can't unblock manually.");
                    } else {
                        r.removeDish(r.getMenu().iterator().next().getId());
                    }
                }
            } else {
                System.out.println("Top 3 Dishes:");
                DataStore.restaurants.values().stream()
                        .flatMap(r -> r.getMenu().stream())
                        .sorted((d1, d2) -> Integer.compare(d2.getTotalOrders(), d1.getTotalOrders()))
                        .limit(3)
                        .forEach(d -> System.out.printf("%s (%s): %d orders%n",
                                d.getId(), d.getName(), d.getTotalOrders()));

                System.out.println("Top Restaurant by Revenue:");
                DataStore.restaurants.values().stream()
                        .max(Comparator.comparingDouble(Restaurant::getTotalRevenue))
                        .ifPresent(r -> System.out.printf("%s: ₹%.2f%n", r.getName(), r.getTotalRevenue()));

                System.out.println("Top Dish by Rating:");
                DataStore.restaurants.values().stream()
                        .flatMap(r -> r.getMenu().stream())
                        .filter(d -> d.getTotalRatings() > 0)
                        .max(Comparator.comparingDouble(Dish::getAverageRating))
                        .ifPresent(d -> System.out.printf("%s: %.2f stars%n", d.getName(), d.getAverageRating()));
            }
        }
    }
}

