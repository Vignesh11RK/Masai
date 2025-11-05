package com.vig.ConferenceRoomBooking1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private int userId;
    private String name;
    private List<Booking> bookings = new ArrayList<>();
}
