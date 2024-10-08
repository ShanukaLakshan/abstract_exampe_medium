package com.example.vehiclebooking.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.vehiclebooking.common.ErrorMessages;
import com.example.vehiclebooking.dto.responses.ResponseObject;
import com.example.vehiclebooking.exceptions.BookingException;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public abstract class AbstractController {

    protected <T> ResponseEntity<ResponseObject> sendResponse(T response, HttpStatus httpStatus) {
        ResponseObject responseBody = new ResponseObject();
        responseBody.setData(response);
        responseBody.setStatus(httpStatus);
        return new ResponseEntity<>(responseBody, httpStatus);
    }

    protected ResponseEntity<Void> sendNoContentResponse() {
        return ResponseEntity.noContent().build();
    }

    protected <T> ResponseEntity<ResponseObject> sendSuccessResponse(T response) {
        return sendResponse(response, HttpStatus.OK);
    }

    protected <T> ResponseEntity<ResponseObject> sendCreatedResponse(T response) {
        return sendResponse(response, HttpStatus.CREATED);
    }

    @ResponseBody
    @ExceptionHandler(value = BookingException.class)
    protected ResponseEntity<ResponseObject> handleBookingException(BookingException exception) {
        log.error("Exception occurred", exception);
        Map<String, Object> errorData = new HashMap<>();
        errorData.put("message", exception.getMessage());
        errorData.put("errors", exception.getErrors());
        errorData.put("timeStamp", exception.getTimestamp());

        ResponseObject responseBody = new ResponseObject();
        responseBody.setData(errorData);
        responseBody.setStatus(exception.getHttpStatusCode());

        return new ResponseEntity<>(responseBody, exception.getHttpStatusCode());
    }

    @ResponseBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    protected ResponseEntity<ResponseObject> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException exception) {
        log.error("Exception occurred", exception);
        List<String> errors = exception.getBindingResult().getFieldErrors().stream().map(FieldError::getDefaultMessage)
                .collect(Collectors.toList());
        Map<Object, Object> errorData = new HashMap<>();
        errorData.put("errors", errors);
        errorData.put("message", ErrorMessages.ERR_MSG_BAD_REQUEST);
        errorData.put("timeStamp", new Date());

        ResponseObject responseBody = new ResponseObject();
        responseBody.setData(errorData);
        responseBody.setStatus(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
    }
}
