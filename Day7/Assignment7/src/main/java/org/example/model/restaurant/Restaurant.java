package org.example.model.restaurant;

import org.example.service.observer.PriceObserver;

import java.util.*;

public class Restaurant implements PriceObserver {
    private String id, name;
    private boolean blocked = false;
    private Map<String, Dish> menu = new HashMap<>();
    private List<PriceObserver> competitors = new ArrayList<>();
    private double totalRevenue = 0.0;

    public Restaurant(String id, String name) {
        this.id = id; this.name = name;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public boolean isBlocked() { return blocked; }
    public double getTotalRevenue() { return totalRevenue; }

    public void addDish(Dish dish) {
        menu.put(dish.getId(), dish);
        checkMenuSize();
    }
    public void removeDish(String dishId) {
        menu.remove(dishId);
        checkMenuSize();
    }

    private void checkMenuSize() {
        blocked = menu.size() < 5;
    }

    public Collection<Dish> getMenu() {
        return menu.values();
    }

    public void attachCompetitor(PriceObserver other) {
        if (other != this) competitors.add(other);
    }

    public void setPrice(String dishId, double newPrice) {
        Dish d = menu.get(dishId);
        if (d == null) return;
        double old = d.getPrice();
        d.setPrice(newPrice);
        if ((old - newPrice) / old > 0.15) {
            competitors.forEach(c -> c.updatePrice(dishId, newPrice));
        }
    }

    @Override
    public void updatePrice(String dishId, double newPrice) {
        Dish d = menu.get(dishId);
        if (d != null) {
            double adjusted = d.getPrice() * 0.95;
            d.setPrice(adjusted);
        }
    }

    public void recordSale(String dishId, double amount) {
        Dish d = menu.get(dishId);
        if (d != null) {
            d.incrementOrders();
            totalRevenue += amount;
        }
    }

    public Optional<Dish> findDish(String dishId) {
        return Optional.ofNullable(menu.get(dishId));
    }
}
