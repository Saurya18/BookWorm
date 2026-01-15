package com.example.demo.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import com.example.demo.util.JwtUtil;
import com.example.demo.util.PasswordUtil;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public String register(User user) {
        user.setPassword(
                PasswordUtil.hashPassword(user.getPassword())
        );
        userRepository.save(user);
        return "User registered successfully";
    }

    @Override
    public String login(String username, String password) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!PasswordUtil.verifyPassword(password, user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        return jwtUtil.generateToken(username);
    }
}
