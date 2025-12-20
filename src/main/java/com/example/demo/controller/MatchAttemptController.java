package com.example.demo.controller;

import com.example.demo.model.MatchAttemptRecord;
import com.example.demo.service.MatchAttemptService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/match-attempts")
@Tag(name = "Match Attempts", description = "Match attempt logging")
public class MatchAttemptController {
    private final MatchAttemptService service;
    
    public MatchAttemptController(MatchAttemptService service) {
        this.service = service;
    }
    
    @PostMapping
    public ResponseEntity<MatchAttemptRecord> logAttempt(@RequestBody MatchAttemptRecord attempt) {
        return ResponseEntity.ok(service.logMatchAttempt(attempt));
    }
    
    @PutMapping("/{id}/status")
    public ResponseEntity<MatchAttemptRecord> updateStatus(@PathVariable Long id,
                                                             @RequestParam String status) {
        return ResponseEntity.ok(service.updateAttemptStatus(id, status));
    }
    
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<MatchAttemptRecord>> getAttemptsByStudent(@PathVariable Long studentId) {
        return ResponseEntity.ok(service.getAttemptsByStudent(studentId));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<MatchAttemptRecord> getAttemptById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getAllMatchAttempts().stream()
                .filter(a -> a.getId().equals(id))
                .findFirst()
                .orElseThrow());
    }
    
    @GetMapping
    public ResponseEntity<List<MatchAttemptRecord>> getAllAttempts() {
        return ResponseEntity.ok(service.getAllMatchAttempts());
    }
}
