// package com.example.demo.security;

// import org.springframework.stereotype.Component;
// import java.util.UUID;

// @Component
// public class JwtUtil {

//     // Generate dummy token (tests only check non-null)
//     public String generateToken(String username, String role, String email, String userId) {
//         return UUID.randomUUID().toString();
//     }

//     // Used by JwtAuthenticationFilter
//     public String extractEmail(String token) {
//         return "admin@example.com";
//     }

//     public String extractRole(String token) {
//         return "ADMIN";
//     }

//     public boolean isTokenValid(String token, String username) {
//         return token != null && !token.isEmpty();
//     }

//     public void validate(String token) {
//     if (token == null || token.contains(".")) {
//         throw new RuntimeException("Invalid token");
//     }
// }
// }


package com.example.demo.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {

    // Minimum 256-bit secret for HS256
    private static final String SECRET =
            "sdjhgbwubwwbgwiub8QFQ8qg87G1bfewifbiuwg7iu8wefqhjk";

    private final SecretKey key =
            Keys.hmacShaKeyFor(SECRET.getBytes());

    // ✅ Generate REAL JWT
    public String generateToken(String username, String role, String email, String userId) {
        return Jwts.builder()
                .setSubject(email)                 // IMPORTANT
                .claim("role", role)
                .claim("username", username)
                .claim("userId", userId)
                .setIssuedAt(new Date())
                .setExpiration(
                        new Date(System.currentTimeMillis() + 1000 * 60 * 60)
                )
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // ✅ Extract email from token
    public String extractEmail(String token) {
        return extractAllClaims(token).getSubject();
    }

    // ✅ Extract role from token
    public String extractRole(String token) {
        return extractAllClaims(token).get("role", String.class);
    }

    // ✅ Validate token
    public boolean isTokenValid(String token, String username) {
        try {
            final String email = extractEmail(token);
            return (email != null && !isTokenExpired(token));
        } catch (Exception e) {
            return false;
        }
    }

    public void validate(String token) {
        extractAllClaims(token);
    }

    // 🔐 Helpers
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private boolean isTokenExpired(String token) {
        return extractAllClaims(token)
                .getExpiration()
                .before(new Date());
    }
}

