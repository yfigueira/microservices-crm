package org.example.activityservice.exceptionhandler;

import org.example.activityservice.exception.ActivityServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@RestControllerAdvice
public class ActivityServiceExceptionHandler {

    @ExceptionHandler(ActivityServiceException.ResourceNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleNotFoundException(ActivityServiceException.ResourceNotFoundException ex) {
        var exceptionResponse = ExceptionResponse.builder()
                .message(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exceptionResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleInvalidArgumentException(MethodArgumentNotValidException ex) {
        Set<String> errors = new HashSet<>();

        ex.getBindingResult().getAllErrors()
                .forEach(error -> {
                    var errorMessage = error.getDefaultMessage();
                    errors.add(errorMessage);
                });

        var exceptionResponse = ExceptionResponse.builder()
                .validationErrors(errors)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exceptionResponse);
    }

    @ExceptionHandler(ActivityServiceException.ActionNotAllowedException.class)
    public ResponseEntity<ExceptionResponse> handleActionNotAllowedException(ActivityServiceException.ActionNotAllowedException ex) {
        var exceptionResponse = ExceptionResponse.builder()
                .message(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exceptionResponse);
    }
}
