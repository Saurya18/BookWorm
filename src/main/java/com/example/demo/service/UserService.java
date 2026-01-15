package com.example.demo.service;

import com.example.demo.entity.User;

public interface UserService {
    String register(User user);
    String login(String username, String password);
}
