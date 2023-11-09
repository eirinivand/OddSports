package com.evandorou.oddsports;

import com.evandorou.oddsports.exception.MatchNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MatchNotFoundException.class)
    public ResponseEntity<ApiError> handleMatchNotFoundException(MatchNotFoundException ex, WebRequest request) {
        ApiError apiError = new com.evandorou.oddsports.ApiError(HttpStatus.NOT_FOUND, ex.getMessage());
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }
}
