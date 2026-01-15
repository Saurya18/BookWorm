package com.example.demo.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseUtil {

    public static ResponseEntity<String> ok(String msg) {
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    public static ResponseEntity<String> badRequest(String msg) {
        return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
    }

    public static ResponseEntity<String> unauthorized(String msg) {
        return new ResponseEntity<>(msg, HttpStatus.UNAUTHORIZED);
    }
}
