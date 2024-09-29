package com.example.vehiclebooking.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.vehiclebooking.dto.BookingRequest;
import com.example.vehiclebooking.dto.BookingResponse;
import com.example.vehiclebooking.exceptions.BookingException;
import com.example.vehiclebooking.model.Booking;
import com.example.vehiclebooking.repository.BookingRepository;
import com.example.vehiclebooking.services.BookingService;
import com.example.vehiclebooking.common.ErrorMessages;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public BookingResponse bookVehicle(BookingRequest bookingRequest) {
        if (bookingRequest == null) {
            throw new BookingException(null, ErrorMessages.BOOKING_REQUEST_NULL);
        }
        return new BookingResponse();
    }

    @Override
    public List<BookingResponse> getBookingHistory(String userId) {
        List<Booking> bookings = bookingRepository.findByUserId(userId);
        return bookings.stream().map(booking -> new BookingResponse()).collect(Collectors.toList());
    }

}
