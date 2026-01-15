package com.example.demo.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secret:MySecretKeyForJWTTokenGeneration12345678901234567890}")
    private String secret;

    private SecretKey getSigningKey() {
        byte[] keyBytes = secret.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 20 * 60 * 1000)) // 20 min
                .signWith(getSigningKey())
                .compact();
    }

    public String validateTokenAndGetUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }


    public String extractUsername(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (Exception e) {
            return null;
        }
    }
    public boolean validateToken(String token, String username) {
        String extractedUser = extractUsername(token);
        if (extractedUser == null) {
            return false;
        }
        return (extractedUser.equals(username) && !isTokenExpired(token));
    }


    private Date extractExpiration(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getExpiration();
        } catch (Exception e) {
            return new Date(0); // Return past date if token is invalid
        }
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }


}
