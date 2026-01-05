package com.example.demo.util;

import org.springframework.http.ResponseEntity;

public class ResponseUtil {
    public static ResponseEntity<String> success(String msg) {
        return ResponseEntity.status(200).body(msg);
    }

    public static ResponseEntity<String> badRequest(String msg) {
        return ResponseEntity.status(400).body(msg);
    }

    public static ResponseEntity<String> notFound(String msg) {
        return ResponseEntity.status(404).body(msg);
    }

    public static ResponseEntity<String> serverError(String msg) {
        return ResponseEntity.status(500).body(msg);
    }
}
