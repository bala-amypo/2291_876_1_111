package com.example.demo.controller;

import com.example.demo.model.CompatibilityScoreRecord;
import com.example.demo.service.CompatibilityScoreService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/compatibility")
@Tag(name = "Compatibility Scores", description = "Compatibility score computation")
@RequiredArgsConstructor
public class CompatibilityScoreController {
    private final CompatibilityScoreService compatibilityScoreService;

    @PostMapping("/compute/{studentAId}/{studentBId}")
    public ResponseEntity<CompatibilityScoreRecord> computeScore(
            @PathVariable Long studentAId, @PathVariable Long studentBId) {
        return ResponseEntity.ok(compatibilityScoreService.computeScore(studentAId, studentBId));
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<CompatibilityScoreRecord>> getScoresForStudent(@PathVariable Long studentId) {
        return ResponseEntity.ok(compatibilityScoreService.getScoresForStudent(studentId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompatibilityScoreRecord> getScoreById(@PathVariable Long id) {
        return ResponseEntity.ok(compatibilityScoreService.getScoreById(id));
    }

    @GetMapping
    public ResponseEntity<List<CompatibilityScoreRecord>> getAllScores() {
        return ResponseEntity.ok(compatibilityScoreService.getAllScores());
    }
}
