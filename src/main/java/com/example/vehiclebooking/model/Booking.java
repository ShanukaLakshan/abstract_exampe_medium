package com.example.vehiclebooking.model;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
@EntityScan
public class Booking {
    @Id
    private String id;
    private String userId;
    private String vehicleId;
    private String status;
    private String bookingDate;
}
