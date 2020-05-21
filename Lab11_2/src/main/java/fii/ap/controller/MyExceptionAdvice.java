package fii.ap.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class MyExceptionAdvice {
    @ExceptionHandler(value = PlayerNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleGenericNotFoundException(PlayerNotFoundException e) {
        ErrorResponse error = new ErrorResponse(e.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
