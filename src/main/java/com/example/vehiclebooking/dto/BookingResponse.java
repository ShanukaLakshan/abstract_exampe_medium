package com.example.vehiclebooking.dto;

import lombok.Data;

@Data
public class BookingResponse {
    private String bookingId;
    private String userId;
    private String vehicleId;
    private String status;
    private String bookingDate;
}
