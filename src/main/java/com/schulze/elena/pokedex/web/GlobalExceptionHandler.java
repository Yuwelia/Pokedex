package com.schulze.elena.pokedex.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    //@ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleAllExceptions(Exception ex) {
        // Logs the full stack trace at ERROR level
        log.error("Unhandled exception caught in web layer:", ex); 

        if (ex instanceof ResponseStatusException responseStatusException) {
            return ResponseEntity
                .status(responseStatusException.getStatusCode())
                .body(responseStatusException.getMessage());
        } else {
            return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("An internal error occurred.");
        }
    }
}