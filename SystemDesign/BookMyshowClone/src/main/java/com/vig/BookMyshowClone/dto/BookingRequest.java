package com.vig.BookMyshowClone.dto;


import java.util.List;

public class BookingRequest {
    private Long userId;
    private Long showId;
    private List<Long> seatIds;

    // Constructors
    public BookingRequest() {}

    public BookingRequest(Long userId, Long showId, List<Long> seatIds) {
        this.userId = userId;
        this.showId = showId;
        this.seatIds = seatIds;
    }

    // Getters & Setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getShowId() {
        return showId;
    }

    public void setShowId(Long showId) {
        this.showId = showId;
    }

    public List<Long> getSeatIds() {
        return seatIds;
    }

    public void setSeatIds(List<Long> seatIds) {
        this.seatIds = seatIds;
    }
}
