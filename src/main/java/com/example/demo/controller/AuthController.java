package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        User saved = userService.register(user);
        return saved;
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        String res = userService.login(user.getUsername(), user.getPassword());
        return res;
    }
}
