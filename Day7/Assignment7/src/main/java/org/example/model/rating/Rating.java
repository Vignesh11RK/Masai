package org.example.model.rating;


public class Rating {
    private String dishId;
    private int stars; // 1–5 rating

    public Rating(String dishId, int stars) {
        this.dishId = dishId;
        this.stars = stars;
    }

    public String getDishId() { return dishId; }
    public int getStars() { return stars; }
}