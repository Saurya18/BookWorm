package com.example.demo.controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;
import java.util.Collections;
import java.util.HashMap;

import com.example.demo.service.UserService;
import com.example.demo.entity.User;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;


@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication", description = "Authentication APIs for user registration and login")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    @Operation(summary = "Register a new user", description = "Creates a new user account")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "User registered successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public ResponseEntity<Map<String, String>> register(@RequestBody User user) {
        try {
            String message = userService.register(user);
            Map<String, String> response = new HashMap<>();
            response.put("message", message);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @PostMapping("/login")
    @Operation(summary = "User login", description = "Authenticates user and returns JWT token")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Login successful", 
                    content = @Content(schema = @Schema(implementation = Map.class))),
        @ApiResponse(responseCode = "401", description = "Invalid credentials")
    })
    public ResponseEntity<Map<String, String>> login(@RequestBody User user) {
        try {
            String token = userService.login(
                    user.getUsername(),
                    user.getPassword()
            );
            Map<String, String> response = new HashMap<>();
            response.put("jwtToken", token);
            response.put("message", "Login successful");
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            Map<String, String> response = new HashMap<>();
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }
}
