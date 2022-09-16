package com.geekbrains.callservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<?> catchDataValidationException (CallRequestException e) {
        return new ResponseEntity<>(new MarketError(e.getMessages()),HttpStatus.BAD_REQUEST);
    }
}


