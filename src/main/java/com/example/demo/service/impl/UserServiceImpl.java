package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User register(User user) {
        try {
            user.setCreatedAt(LocalDateTime.now());
            user.setUpdatedAt(LocalDateTime.now());
            return userRepository.save(user);
        } catch (Exception e) {
            throw new RuntimeException("User registration error: " + e.getMessage());
        }
    }

    @Override
    public String login(String username, String password) {
        try {
            User u = userRepository.findByUsername(username);
            if (u == null) {
                throw new RuntimeException("User not found");
            }
            if (!u.getPassword().equals(password)) {
                throw new RuntimeException("Wrong password");
            }
            return "Login success for user: " + username;
        } catch (Exception e) {
            throw new RuntimeException("Login error: " + e.getMessage());
        }
    }
}
