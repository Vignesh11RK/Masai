package com.vig.RoomBooking.model;

import java.time.LocalDateTime;
import java.util.*;

public class ConferenceRoom {
    private String id;
    private String name;
    private int capacity;
    private List<String> equipment;
    private String location;
    private List<Booking> bookings;

    public ConferenceRoom(String id, String name, int capacity, List<String> equipment, String location) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.equipment = equipment;
        this.location = location;
        this.bookings = new ArrayList<>();
    }

    public boolean isAvailable(LocalDateTime start, LocalDateTime end) {
        for (Booking booking : bookings) {
            if (booking.overlapsWith(start, end)) {
                return false;
            }
        }
        return true;
    }

    public void addBooking(Booking booking) {
        bookings.add(booking);
    }

    public void removeBooking(Booking booking) {
        bookings.remove(booking);
    }

    public String getId() {
        return id;
    }

    public int getCapacity() {
        return capacity;
    }

    public List<String> getEquipment() {
        return equipment;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }
}
