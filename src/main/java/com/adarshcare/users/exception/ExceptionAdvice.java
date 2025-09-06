package com.adarshcare.users.exception;

import java.util.Date;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> handleGenericException(Exception ex) {
        return ResponseEntity.status(500).body(new Error(new Date(), ex.getMessage(), 500));
    }

    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<Error> handleResourceNotFound(ResourceNotFound ex) {
        return ResponseEntity.status(404).body(new Error(new Date(), ex.getMessage(), 404));
    }
    
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Error> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.status(400).body(new Error(new Date(), ex.getMessage(), 400));
    }
}
