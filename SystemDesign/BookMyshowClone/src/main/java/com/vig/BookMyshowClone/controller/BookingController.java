package com.vig.BookMyshowClone.controller;


import com.vig.BookMyshowClone.dto.BookingRequest;
import com.vig.BookMyshowClone.dto.BookingResponse;
import com.vig.BookMyshowClone.service.BookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService bookingService;

    // Constructor Injection (recommended)
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    // POST /bookings
    @PostMapping
    public ResponseEntity<BookingResponse> bookSeats(@RequestBody BookingRequest request) {
        BookingResponse response = bookingService.bookSeats(request);
        return ResponseEntity.ok(response);
    }
}
