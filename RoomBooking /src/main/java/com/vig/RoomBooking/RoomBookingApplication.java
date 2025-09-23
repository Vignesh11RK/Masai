package com.vig.RoomBooking;

import com.vig.RoomBooking.model.Booking;
import com.vig.RoomBooking.model.ConferenceRoom;
import com.vig.RoomBooking.model.User;
import com.vig.RoomBooking.service.BookingManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class RoomBookingApplication {

	public static void main(String[] args) {
		SpringApplication.run(RoomBookingApplication.class, args);
        BookingManager manager = new BookingManager();

        // Sample users
        User alice = new User("u1", "Alice");
        User bob = new User("u2", "Bob");
        manager.addUser(alice);
        manager.addUser(bob);

        // Sample conference rooms
        manager.addRoom(new ConferenceRoom("r1", "Ocean View", 10, Arrays.asList("TV", "Whiteboard"), "1st Floor"));
        manager.addRoom(new ConferenceRoom("r2", "Mountain Hall", 5, Arrays.asList("Whiteboard"), "2nd Floor"));

        // View available rooms
        LocalDateTime start = LocalDateTime.now().plusHours(1);
        LocalDateTime end = start.plusHours(2);

        System.out.println("Available rooms:");
        List<ConferenceRoom> available = manager.getAvailableRooms(start, end, 4, Arrays.asList("Whiteboard"));
        for (ConferenceRoom room : available) {
            System.out.println("- " + room.getName());
        }

        // Book a room
        Booking booking1 = manager.bookRoom("u1", "r1", start, end);
        if (booking1 != null) {
            System.out.println("Booking successful: " + booking1);
        }

        // Try double booking
        Booking booking2 = manager.bookRoom("u2", "r1", start, end);
        if (booking2 == null) {
            System.out.println("Double booking prevented.");
        }

        // Cancel booking
        boolean cancelled = manager.cancelBooking(booking1.getId());
        System.out.println("Cancellation status: " + cancelled);

        // User bookings
        List<Booking> userBookings = manager.getUserBookings("u1");
        System.out.println("Alice's bookings:");
        for (Booking b : userBookings) {
            System.out.println(b);
        }
	}

}
