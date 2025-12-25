package com.example.demo.controller;

import com.example.demo.model.CompatibilityScoreRecord;
import com.example.demo.service.CompatibilityScoreService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/matches")
public class MatchController {

    private final CompatibilityScoreService service;

    public MatchController(CompatibilityScoreService service) {
        this.service = service;
    }

    @PostMapping("/compute")
    public ResponseEntity<CompatibilityScoreRecord> compute(
            @RequestParam Long a,
            @RequestParam Long b) {
        return ResponseEntity.ok(service.computeScore(a, b));
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<List<CompatibilityScoreRecord>> byStudent(
            @PathVariable Long id) {
        return ResponseEntity.ok(service.getScoresForStudent(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompatibilityScoreRecord> byId(
            @PathVariable Long id) {
        return ResponseEntity.ok(service.getScoreById(id));
    }
}
