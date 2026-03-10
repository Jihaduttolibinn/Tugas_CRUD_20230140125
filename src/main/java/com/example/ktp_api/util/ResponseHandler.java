package com.example.ktp_api.util;

import com.example.ktp_api.model.WebResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHandler {
    public static <T> ResponseEntity<Object> generateResponse(String message, HttpStatus status, T responseObj) {
        WebResponse<T> response = WebResponse.<T>builder()
                .message(message)
                .status(status.value())
                .data(responseObj)
                .build();

        return new ResponseEntity<>(response, status);
    }
    
}
