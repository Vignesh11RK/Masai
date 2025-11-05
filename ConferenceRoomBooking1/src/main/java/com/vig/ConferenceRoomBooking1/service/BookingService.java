package com.vig.ConferenceRoomBooking1.service;

import com.vig.ConferenceRoomBooking1.model.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@Service
public class BookingService {

    private final Map<Integer, User> users = new HashMap<>();
    private final List<Building> buildings = new ArrayList<>();

    public BookingService() {
        // Initialize dummy data
        Building b1 = new Building(1, "Headquarters", new ArrayList<>());
        ConferenceRoom r1 = new ConferenceRoom(101, "Room A", 10, Arrays.asList("Projector", "Whiteboard"), new ArrayList<>());
        ConferenceRoom r2 = new ConferenceRoom(102, "Room B", 8, Arrays.asList("TV"), new ArrayList<>());
        b1.getRooms().addAll(List.of(r1, r2));
        buildings.add(b1);

        users.put(1, new User(1, "Alice", new ArrayList<>()));
        users.put(2, new User(2, "Bob", new ArrayList<>()));
    }

    public String bookRoom(int userId, int buildingId, int roomId,
                           LocalDate date, LocalTime start, LocalTime end) {

        User user = users.get(userId);
        if (user == null) return "Invalid user.";

        Building building = buildings.stream()
                .filter(b -> b.getBuildingId() == buildingId)
                .findFirst().orElse(null);
        if (building == null) return "Invalid building.";

        ConferenceRoom room = building.getRoomById(roomId);
        if (room == null) return "Invalid room.";

        if (!room.isAvailable(date, start, end)) return "Room not available for given time slot.";

        Booking booking = new Booking(user, room, date, start, end);
        room.getBookings().add(booking);
        user.getBookings().add(booking);

        return "✅ Booking confirmed for " + user.getName() + " in " + room.getName();
    }

    public String cancelBooking(int userId, int bookingId) {
        User user = users.get(userId);
        if (user == null) return "Invalid user.";

        for (Building b : buildings) {
            for (ConferenceRoom r : b.getRooms()) {
                for (Booking bk : new ArrayList<>(r.getBookings())) {
                    if (bk.getBookingId() == bookingId && bk.getUser().equals(user)) {
                        r.getBookings().remove(bk);
                        user.getBookings().remove(bk);
                        return "🗑️ Booking cancelled successfully.";
                    }
                }
            }
        }
        return "Booking not found.";
    }

    public List<Booking> getUserBookings(int userId) {
        User user = users.get(userId);
        return user != null ? user.getBookings() : Collections.emptyList();
    }

    public List<ConferenceRoom> getAvailableRooms(LocalDate date, LocalTime start, LocalTime end) {
        List<ConferenceRoom> available = new ArrayList<>();
        for (Building b : buildings) {
            for (ConferenceRoom r : b.getRooms()) {
                if (r.isAvailable(date, start, end)) {
                    available.add(r);
                }
            }
        }
        return available;
    }
}
