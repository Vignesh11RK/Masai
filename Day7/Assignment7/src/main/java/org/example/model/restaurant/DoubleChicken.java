package org.example.model.restaurant;

public class DoubleChicken extends AddOnDecorator {
    private static final double ADDON_PRICE = 50;
    public DoubleChicken(Dish dish) { super(dish); }

    @Override public double getPrice() {
        return dish.getPrice() + ADDON_PRICE;
    }

    @Override public String getName() {
        return dish.getName() + " + Double Chicken";
    }
}
