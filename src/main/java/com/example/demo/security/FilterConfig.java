package com.example.demo.security;

import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    // This configuration is now handled by SecurityConfig
    // Keeping this file for backward compatibility but disabling the bean
    // The filter is now properly injected via SecurityFilterChain
}
