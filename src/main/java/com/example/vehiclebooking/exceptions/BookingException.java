package com.example.vehiclebooking.exceptions;

import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class BookingException extends RuntimeException {
    private final List<String> errors;
    private final HttpStatus httpStatusCode;
    private final Date timestamp;

    public BookingException(HttpStatus httpStatusCode, String message, List<String> errors) {
        super(message, null);
        this.httpStatusCode = httpStatusCode;
        this.errors = errors;
        this.timestamp = new Date();
    }

    public BookingException(HttpStatus httpStatusCode, String message) {
        super(message);
        this.httpStatusCode = httpStatusCode;
        this.errors = null;
        this.timestamp = new Date();
    }

    public BookingException(HttpStatus httpStatusCode, String message, List<String> errors, Throwable throwable) {
        super(message, throwable);
        this.httpStatusCode = httpStatusCode;
        this.errors = errors;
        this.timestamp = new Date();
    }

    public HttpStatus getHttpStatusCode() {
        return (httpStatusCode != null) ? httpStatusCode : HttpStatus.INTERNAL_SERVER_ERROR;
    }

    @Override
    public String toString() {
        return "BookingException{" +
                "httpStatusCode=" + httpStatusCode +
                ", errors=" + errors +
                ", timestamp=" + timestamp +
                '}';
    }

}
