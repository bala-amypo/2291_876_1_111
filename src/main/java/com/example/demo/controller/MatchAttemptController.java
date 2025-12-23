package com.example.demo.controller;

import com.example.demo.model.MatchAttemptRecord;
import com.example.demo.service.MatchAttemptService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/match-attempts")
@Tag(name = "Match Attempts")
public class MatchAttemptController {

    private final MatchAttemptService service;

    public MatchAttemptController(MatchAttemptService service) {
        this.service = service;
    }

    @PostMapping
    public MatchAttemptRecord logAttempt(
            @RequestBody MatchAttemptRecord attempt) {
        return service.logMatchAttempt(attempt);
    }

    @PutMapping("/{id}/status")
    public MatchAttemptRecord updateStatus(
            @PathVariable Long id,
            @RequestParam String status) {
        return service.updateAttemptStatus(id, status);
    }

    @GetMapping("/student/{studentId}")
    public List<MatchAttemptRecord> getByStudent(
            @PathVariable Long studentId) {
        return service.getAttemptsByStudent(studentId);
    }

    @GetMapping("/{id}")
    public MatchAttemptRecord getById(@PathVariable Long id) {
        return service.getAllMatchAttempts()
                .stream()
                .filter(a -> a.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("not found"));
    }

    @GetMapping
    public List<MatchAttemptRecord> getAll() {
        return service.getAllMatchAttempts();
    }
}
