package com.vig.RoomBooking.service;

import com.vig.RoomBooking.model.Booking;
import com.vig.RoomBooking.model.ConferenceRoom;
import com.vig.RoomBooking.model.User;

import java.time.LocalDateTime;
import java.util.*;

public class BookingManager {
    private Map<String, ConferenceRoom> rooms = new HashMap<>();
    private Map<String, User> users = new HashMap<>();
    private Map<String, Booking> allBookings = new HashMap<>();

    public void addRoom(ConferenceRoom room) {
        rooms.put(room.getId(), room);
    }

    public void addUser(User user) {
        users.put(user.getId(), user);
    }

    public List<ConferenceRoom> getAvailableRooms(LocalDateTime start, LocalDateTime end, int minCapacity, List<String> requiredEquipment) {
        List<ConferenceRoom> available = new ArrayList<>();
        for (ConferenceRoom room : rooms.values()) {
            if (room.getCapacity() >= minCapacity &&
                    room.getEquipment().containsAll(requiredEquipment) &&
                    room.isAvailable(start, end)) {
                available.add(room);
            }
        }
        return available;
    }

    public Booking bookRoom(String userId, String roomId, LocalDateTime start, LocalDateTime end) {
        User user = users.get(userId);
        ConferenceRoom room = rooms.get(roomId);

        if (user == null || room == null) {
            System.out.println("Invalid user or room ID.");
            return null;
        }

        if (start.isBefore(LocalDateTime.now())) {
            System.out.println("Cannot book past time slots.");
            return null;
        }

        if (!room.isAvailable(start, end)) {
            System.out.println("Room not available for the selected time slot.");
            return null;
        }

        Booking booking = new Booking(user, room, start, end);
        room.addBooking(booking);
        user.addBooking(booking);
        allBookings.put(booking.getId(), booking);
        return booking;
    }

    public boolean cancelBooking(String bookingId) {
        Booking booking = allBookings.get(bookingId);
        if (booking == null) {
            System.out.println("Booking not found.");
            return false;
        }

        booking.getRoom().removeBooking(booking);
        booking.getUser().cancelBooking(booking);
        allBookings.remove(bookingId);
        return true;
    }

    public List<Booking> getUserBookings(String userId) {
        User user = users.get(userId);
        if (user == null) return Collections.emptyList();
        return user.getBookings();
    }
}
