package org.example.activityservice.exceptionhandler;

import org.example.activityservice.exception.ActivityServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ActivityServiceExceptionHandler {

    @ExceptionHandler(ActivityServiceException.ResourceNotFoundException.class)
    public ResponseEntity<Exception> handleUserServiceException(ActivityServiceException.ResourceNotFoundException ex) {
        var exceptionResponse = Exception.builder()
                .message(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exceptionResponse);
    }
}
