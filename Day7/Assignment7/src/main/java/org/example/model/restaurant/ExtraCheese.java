package org.example.model.restaurant;

public class ExtraCheese extends AddOnDecorator {
    private static final double ADDON_PRICE = 20;
    public ExtraCheese(Dish dish) { super(dish); }

    @Override public double getPrice() {
        return dish.getPrice() + ADDON_PRICE;
    }

    @Override public String getName() {
        return dish.getName() + " + Extra Cheese";
    }
}