package com.example.demo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
@Component
public class JwtUtil {

    public String generateToken(String u, String r, String e, String id) {
        return UUID.randomUUID().toString();
    }

    public void validate(String token) {
        if (token == null || token.length() < 10)
            throw new RuntimeException("invalid token");
    }
}
