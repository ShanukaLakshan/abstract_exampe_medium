package com.example.vehiclebooking.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class BookingRequest {
    @NotEmpty
    private String userId;

    @NotEmpty
    private String vehicleId;

    // Add other necessary fields and validation annotations
}
