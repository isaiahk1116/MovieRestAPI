package com.movie.movieapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        ApiException apiException = new ApiException(new Date(), HttpStatus.NOT_FOUND.toString(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(apiException, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> apiExceptionHandler(Exception ex, WebRequest request) {
        ApiException apiException = new ApiException(new Date(), HttpStatus.INTERNAL_SERVER_ERROR.toString(),ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(apiException, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
