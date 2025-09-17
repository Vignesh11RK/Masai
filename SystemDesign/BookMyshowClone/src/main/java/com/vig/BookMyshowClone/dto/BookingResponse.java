package com.vig.BookMyshowClone.dto;

import java.util.List;

public class BookingResponse {
    private String status;
    private List<Long> bookedSeatIds;
    private Long bookingId;

    public BookingResponse() {}

    public BookingResponse(String status, List<Long> bookedSeatIds, Long bookingId) {
        this.status = status;
        this.bookedSeatIds = bookedSeatIds;
        this.bookingId = bookingId;
    }

    // Getters & Setters
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Long> getBookedSeatIds() {
        return bookedSeatIds;
    }

    public void setBookedSeatIds(List<Long> bookedSeatIds) {
        this.bookedSeatIds = bookedSeatIds;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }
}

