package com.vig.RoomBooking.model;

import java.util.*;

public class User {
    private String id;
    private String name;
    private List<Booking> bookings;

    public User(String id, String name) {
        this.id = id;
        this.name = name;
        this.bookings = new ArrayList<>();
    }

    public void addBooking(Booking booking) {
        bookings.add(booking);
    }

    public void cancelBooking(Booking booking) {
        bookings.remove(booking);
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
