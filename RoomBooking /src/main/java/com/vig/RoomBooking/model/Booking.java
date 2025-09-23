package com.vig.RoomBooking.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Booking {
    private String id;
    private User user;
    private ConferenceRoom room;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Booking(User user, ConferenceRoom room, LocalDateTime startTime, LocalDateTime endTime) {
        this.id = UUID.randomUUID().toString();
        this.user = user;
        this.room = room;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public boolean overlapsWith(LocalDateTime start, LocalDateTime end) {
        return (start.isBefore(endTime) && end.isAfter(startTime));
    }

    public String getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public ConferenceRoom getRoom() {
        return room;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id='" + id + '\'' +
                ", room=" + room.getName() +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
