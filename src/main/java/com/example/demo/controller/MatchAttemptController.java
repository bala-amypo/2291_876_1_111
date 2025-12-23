package com.example.demo.controller;

import com.example.demo.model.MatchAttemptRecord;
import com.example.demo.service.MatchAttemptService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/match-attempts")
@Tag(name = "Match Attempts", description = "Match attempt logging")
public class MatchAttemptController {

    private final MatchAttemptService matchAttemptService;

    public MatchAttemptController(MatchAttemptService matchAttemptService) {
        this.matchAttemptService = matchAttemptService;
    }

    @PostMapping
    @Operation(summary = "Log match attempt")
    public ResponseEntity<MatchAttemptRecord> logAttempt(@RequestBody MatchAttemptRecord attempt) {
        return ResponseEntity.ok(matchAttemptService.logMatchAttempt(attempt));
    }

    @PutMapping("/{id}/status")
    @Operation(summary = "Update attempt status")
    public ResponseEntity<MatchAttemptRecord> updateStatus(@PathVariable Long id, 
                                                           @RequestBody Map<String, String> request) {
        return ResponseEntity.ok(matchAttemptService.updateAttemptStatus(id, request.get("status")));
    }

    @GetMapping("/student/{studentId}")
    @Operation(summary = "Get attempts involving a student")
    public ResponseEntity<List<MatchAttemptRecord>> getAttemptsByStudent(@PathVariable Long studentId) {
        return ResponseEntity.ok(matchAttemptService.getAttemptsByStudent(studentId));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get attempt by ID")
    public ResponseEntity<MatchAttemptRecord> getAttempt(@PathVariable Long id) {
        return ResponseEntity.ok(matchAttemptService.getAllMatchAttempts().stream()
                .filter(a -> a.getId().equals(id))
                .findFirst()
                .orElse(null));
    }

    @GetMapping
    @Operation(summary = "Get all match attempts")
    public ResponseEntity<List<MatchAttemptRecord>> getAllAttempts() {
        return ResponseEntity.ok(matchAttemptService.getAllMatchAttempts());
    }
}