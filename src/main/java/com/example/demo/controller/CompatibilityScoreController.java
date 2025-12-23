package com.example.demo.controller;

import com.example.demo.model.CompatibilityScoreRecord;
import com.example.demo.service.CompatibilityScoreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/compatibility")
@Tag(name = "Compatibility", description = "Compatibility score management")
public class CompatibilityScoreController {

    private final CompatibilityScoreService compatibilityScoreService;

    public CompatibilityScoreController(CompatibilityScoreService compatibilityScoreService) {
        this.compatibilityScoreService = compatibilityScoreService;
    }

    @PostMapping("/compute/{studentAId}/{studentBId}")
    @Operation(summary = "Compute compatibility score")
    public ResponseEntity<CompatibilityScoreRecord> computeScore(@PathVariable Long studentAId, 
                                                                 @PathVariable Long studentBId) {
        return ResponseEntity.ok(compatibilityScoreService.computeScore(studentAId, studentBId));
    }

    @GetMapping("/student/{studentId}")
    @Operation(summary = "Get all scores for a student")
    public ResponseEntity<List<CompatibilityScoreRecord>> getScoresForStudent(@PathVariable Long studentId) {
        return ResponseEntity.ok(compatibilityScoreService.getScoresForStudent(studentId));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get score by ID")
    public ResponseEntity<CompatibilityScoreRecord> getScore(@PathVariable Long id) {
        return ResponseEntity.ok(compatibilityScoreService.getScoreById(id));
    }

    @GetMapping
    @Operation(summary = "Get all compatibility scores")
    public ResponseEntity<List<CompatibilityScoreRecord>> getAllScores() {
        return ResponseEntity.ok(compatibilityScoreService.getAllScores());
    }
}