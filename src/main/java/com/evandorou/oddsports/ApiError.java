package com.evandorou.oddsports;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

public class ApiError {
    private HttpStatus status;
    private String message;
    private LocalDateTime timestamp;

    public ApiError(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

    public org.springframework.http.HttpStatus getStatus() {
        return status;
    }

    public void setStatus(org.springframework.http.HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public java.time.LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(java.time.LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
