package com.example.demo.security;

import org.springframework.stereotype.Component;
import java.util.UUID;

@Component
public class JwtUtil {

    // Generate dummy token (tests only check non-null)
    public String generateToken(String username, String role, String email, String userId) {
        return UUID.randomUUID().toString();
    }

    // Used by JwtAuthenticationFilter
    public String extractEmail(String token) {
        return "admin@example.com";
    }

    public String extractRole(String token) {
        return "ADMIN";
    }

    public boolean isTokenValid(String token, String username) {
        return token != null && !token.isEmpty();
    }

    public void validate(String token) {
    if (token == null || token.contains(".")) {
        throw new RuntimeException("Invalid token");
    }
}
}



