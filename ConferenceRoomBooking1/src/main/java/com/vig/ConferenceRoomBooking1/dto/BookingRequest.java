package com.vig.ConferenceRoomBooking1.dto;

import lombok.Data;

@Data
public class BookingRequest {
    private int userId;
    private int buildingId;
    private int roomId;
    private String date;
    private String startTime;
    private String endTime;
}
