package org.example.model.restaurant;

public abstract class AddOnDecorator implements Dish {
    protected Dish dish;
    public AddOnDecorator(Dish dish) { this.dish = dish; }

    @Override public String getId() { return dish.getId(); }
    @Override public String getName() { return dish.getName(); }
    @Override public void setPrice(double price) { dish.setPrice(price); }
    @Override public void addRating(int rating) { dish.addRating(rating); }
    @Override public double getAverageRating() { return dish.getAverageRating(); }
    @Override public int getTotalRatings() { return dish.getTotalRatings(); }
    @Override public int getTotalOrders() { return dish.getTotalOrders(); }
    @Override public void incrementOrders() { dish.incrementOrders(); }
}
