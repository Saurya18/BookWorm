package com.example.demo.security;

import org.springframework.context.annotation.*;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/auth/**").permitAll()
                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-resources/**", 
                                 "/swagger-ui.html", "/swagger-ui/index.html").permitAll()
                .anyRequest().authenticated()
            )
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()))
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        
        return http.build();
    }

    @Bean
    public FilterRegistrationBean<JwtFilter> jwtFilterRegistration() {
        FilterRegistrationBean<JwtFilter> bean = new FilterRegistrationBean<>();
        bean.setFilter(jwtFilter);
        bean.setEnabled(false); // Disable filter registration as we're using it in SecurityFilterChain
        return bean;
    }
}
