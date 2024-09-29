package com.example.vehiclebooking.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.vehiclebooking.dto.BookingRequest;
import com.example.vehiclebooking.dto.responses.ResponseObject;
import com.example.vehiclebooking.services.BookingService;

import javax.validation.Valid;

@RestController
@RequestMapping("${api.path.booking}")
public class BookingController extends AbstractController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/book")
    public ResponseEntity<ResponseObject> bookVehicle(@Valid @RequestBody BookingRequest bookingRequest) {
        return sendCreatedResponse(bookingService.bookVehicle(bookingRequest));
    }

    @GetMapping("/history/{userId}")
    public ResponseEntity<ResponseObject> getBookingHistory(@PathVariable String userId) {
        System.out.println("userId = " + userId);
        return sendSuccessResponse(bookingService.getBookingHistory(userId));
    }

}
