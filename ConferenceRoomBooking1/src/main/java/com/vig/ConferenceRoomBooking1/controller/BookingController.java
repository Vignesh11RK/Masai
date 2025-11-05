package com.vig.ConferenceRoomBooking1.controller;

import com.vig.ConferenceRoomBooking1.dto.BookingRequest;
import com.vig.ConferenceRoomBooking1.model.Booking;
import com.vig.ConferenceRoomBooking1.model.ConferenceRoom;
import com.vig.ConferenceRoomBooking1.service.BookingService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/book")
    public String bookRoom(@RequestBody BookingRequest req) {
        return bookingService.bookRoom(
                req.getUserId(),
                req.getBuildingId(),
                req.getRoomId(),
                LocalDate.parse(req.getDate()),
                LocalTime.parse(req.getStartTime()),
                LocalTime.parse(req.getEndTime())
        );
    }

    @DeleteMapping("/{userId}/{bookingId}")
    public String cancelBooking(@PathVariable int userId, @PathVariable int bookingId) {
        return bookingService.cancelBooking(userId, bookingId);
    }

    @GetMapping("/user/{userId}")
    public List<Booking> getUserBookings(@PathVariable int userId) {
        return bookingService.getUserBookings(userId);
    }

    @GetMapping("/available")
    public List<ConferenceRoom> getAvailableRooms(@RequestParam String date,
                                                  @RequestParam String start,
                                                  @RequestParam String end) {
        return bookingService.getAvailableRooms(
                LocalDate.parse(date),
                LocalTime.parse(start),
                LocalTime.parse(end)
        );
    }
}
