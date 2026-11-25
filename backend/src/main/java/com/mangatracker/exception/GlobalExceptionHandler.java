package com.mangatracker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/*
           GlobalExceptionHandler

           @RestControllerAdvice,  catches exceptions from all controllers
           exception turned into json responses


            Handled Exceptions:
            ResourceNotFoundException
            - Returns HTTP 404
            - Body contains: timestamp, status, message

            MethodArgumentNotValidException:

            triggers on failures like a blank titile or invalid rating
            returns HTTP 400


            Response shape
            {
              "timestamp":
              "status":
              "message":
              "errors":
            }

*/


@RestControllerAdvice
public class GlobalExceptionHandler {

    // Handle missing resource errors
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNotFound(ResourceNotFoundException exception) {
        return build(HttpStatus.NOT_FOUND, exception.getMessage(), null); // return build response, 404 error when not found

    }

    // Handle duplicates if they exists
    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<Map<String, Object>> handleDuplicate(DuplicateResourceException exception) {
        return build(HttpStatus.CONFLICT, exception.getMessage(), null); // return reponse 409
    }

    // Invalid input validation
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public  ResponseEntity<Map<String, Object>> handleValidation(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new LinkedHashMap<>();
        // set the error fields = to error defaultMessage
        exception.getBindingResult().getFieldErrors().forEach(e -> errors.put(e.getField(), e.getDefaultMessage()));

        return build(HttpStatus.BAD_REQUEST, "Validation error", errors); // return 400, bad request

    }


    // JSON error
    private ResponseEntity<Map<String, Object>> build(HttpStatus status, String message, Object errors) {
        Map<String, Object> body = new HashMap<>(); // create the map
        // Set response bodies
        body.put("timestamp", LocalDateTime.now().toString());
        body.put("status", status.value());
        body.put("message", message);
        if(errors != null) {
            body.put("errors", errors);
        }
        return new ResponseEntity<>(body, status);
    }
}
