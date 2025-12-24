package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.security.JwtUtil;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtUtil jwtUtil;
    private final Map<String, AuthRequest> users = new HashMap<>();

    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AuthRequest req) {
        if (users.containsKey(req.getUsername())) {
            return ResponseEntity.badRequest().body("already exists");
        }
        users.put(req.getUsername(), req);
        return ResponseEntity.ok("registered");
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest req) {
        AuthRequest u = users.get(req.getUsername());
        if (u == null || !u.getPassword().equals(req.getPassword())) {
            return ResponseEntity.status(401).build();
        }
        String token = jwtUtil.generateToken(
                req.getUsername(), req.getRole(), req.getEmail(), "1"
        );
        return ResponseEntity.ok(
                new AuthResponse(token, "1", req.getEmail(), req.getRole())
        );
    }
}
