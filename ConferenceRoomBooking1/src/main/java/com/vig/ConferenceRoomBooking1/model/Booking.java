package com.vig.ConferenceRoomBooking1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
    private static int counter = 1;
    private int bookingId;
    private User user;
    private ConferenceRoom room;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;

    public Booking(User user, ConferenceRoom room, LocalDate date, LocalTime startTime, LocalTime endTime) {
        this.bookingId = counter++;
        this.user = user;
        this.room = room;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public boolean overlaps(Booking other) {
        if (!this.date.equals(other.date)) return false;
        return !(this.endTime.isBefore(other.startTime) || this.startTime.isAfter(other.endTime));
    }
}
