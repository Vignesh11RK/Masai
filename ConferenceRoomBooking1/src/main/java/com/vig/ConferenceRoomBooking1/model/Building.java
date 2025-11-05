package com.vig.ConferenceRoomBooking1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Building {
    private int buildingId;
    private String name;
    private List<ConferenceRoom> rooms = new ArrayList<>();

    public ConferenceRoom getRoomById(int id) {
        return rooms.stream().filter(r -> r.getRoomId() == id).findFirst().orElse(null);
    }
}
