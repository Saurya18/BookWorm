package com.example.demo.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtil {

    private static final BCryptPasswordEncoder encoder =
            new BCryptPasswordEncoder();

    private static final String SALT = "MySaltKey123";

    public static String hashPassword(String password) {
        return encoder.encode(password + SALT);
    }

    public static boolean verifyPassword(String raw, String hashed) {
        return encoder.matches(raw + SALT, hashed);
    }
}
