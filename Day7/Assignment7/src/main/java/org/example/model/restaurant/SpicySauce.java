package org.example.model.restaurant;

public class SpicySauce extends AddOnDecorator {
    private static final double ADDON_PRICE = 10;
    public SpicySauce(Dish dish) { super(dish); }

    @Override public double getPrice() {
        return dish.getPrice() + ADDON_PRICE;
    }

    @Override public String getName() {
        return dish.getName() + " + Spicy Sauce";
    }
}
