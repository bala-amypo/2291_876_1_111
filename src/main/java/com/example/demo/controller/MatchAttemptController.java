package com.example.demo.controller;

import com.example.demo.model.MatchAttemptRecord;
import com.example.demo.service.MatchAttemptService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/match-attempts")
@Tag(name = "Match Attempts", description = "Match attempt logging")
@RequiredArgsConstructor
public class MatchAttemptController {
    private final MatchAttemptService matchAttemptService;

    @PostMapping
    public ResponseEntity<MatchAttemptRecord> logMatchAttempt(@RequestBody MatchAttemptRecord attempt) {
        return ResponseEntity.ok(matchAttemptService.logMatchAttempt(attempt));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<MatchAttemptRecord> updateAttemptStatus(@PathVariable Long id, @RequestParam String status) {
        return ResponseEntity.ok(matchAttemptService.updateAttemptStatus(id, status));
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<MatchAttemptRecord>> getAttemptsByStudent(@PathVariable Long studentId) {
        return ResponseEntity.ok(matchAttemptService.getAttemptsByStudent(studentId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MatchAttemptRecord> getAttemptById(@PathVariable Long id) {
        // Implementation would go here if service method exists
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<MatchAttemptRecord>> getAllMatchAttempts() {
        return ResponseEntity.ok(matchAttemptService.getAllMatchAttempts());
    }
}
