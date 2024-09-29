package com.example.vehiclebooking.services;

import com.example.vehiclebooking.dto.BookingRequest;
import com.example.vehiclebooking.dto.BookingResponse;

import java.util.List;

public interface BookingService {

    BookingResponse bookVehicle(BookingRequest bookingRequest);

    List<BookingResponse> getBookingHistory(String userId);

}
