package com.findserv.root.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

        @ExceptionHandler(IllegalArgumentException.class)
        public ResponseEntity<Map<String, String>> handleIllegalArgument(IllegalArgumentException ex) {
            Map<String, String> body = Map.of("message", ex.getMessage() != null ? ex.getMessage() : "Bad request");
            return ResponseEntity.badRequest().body(body);
        }

}
