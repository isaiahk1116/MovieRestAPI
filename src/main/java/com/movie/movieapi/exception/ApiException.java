package com.movie.movieapi.exception;

import lombok.Data;

import java.util.Date;

@Data
public class ApiException {
    private Date timestamp;
    private String status;
    private String message;
    private String details;

    public ApiException(Date timestamp, String status, String message, String details) {
        this.timestamp = timestamp;
        this.status = status;
        this.message = message;
        this.details = details;
    }
}
