package com.drocketeers.server.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.NoSuchElementException;

@ControllerAdvice
public class ApiExceptionHandler {

    private static final ZonedDateTime DATE_TIME = ZonedDateTime.now(ZoneId.of("Asia/Manila"));

    @ExceptionHandler(value = NoSuchElementException.class)
    public ResponseEntity<ApiResponse> handleNSE (NoSuchElementException e) {
        return new ResponseEntity<>(new ApiResponse(e.getMessage(),DATE_TIME), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = ApiException.class)
    public ResponseEntity<ApiResponse> handleCustomException(ApiException e) {
        return new ResponseEntity<>(new ApiResponse(e.getMessage(), DATE_TIME), e.getStatus());
    }
}
