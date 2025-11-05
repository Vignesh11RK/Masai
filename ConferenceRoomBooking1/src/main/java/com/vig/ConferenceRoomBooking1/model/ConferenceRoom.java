package com.vig.ConferenceRoomBooking1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConferenceRoom {
    private int roomId;
    private String name;
    private int capacity;
    private List<String> equipment = new ArrayList<>();
    private List<Booking> bookings = new ArrayList<>();

    public boolean isAvailable(LocalDate date, LocalTime start, LocalTime end) {
        for (Booking b : bookings) {
            if (b.getDate().equals(date)) {
                if (!(end.isBefore(b.getStartTime()) || start.isAfter(b.getEndTime()))) {
                    return false;
                }
            }
        }
        return true;
    }
}
