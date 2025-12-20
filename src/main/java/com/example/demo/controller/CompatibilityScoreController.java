package com.example.demo.controller;

import com.example.demo.model.CompatibilityScoreRecord;
import com.example.demo.service.CompatibilityScoreService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/compatibility")
@Tag(name = "Compatibility", description = "Compatibility score computation")
public class CompatibilityScoreController {
    private final CompatibilityScoreService service;
    
    public CompatibilityScoreController(CompatibilityScoreService service) {
        this.service = service;
    }
    
    @PostMapping("/compute/{studentAId}/{studentBId}")
    public ResponseEntity<CompatibilityScoreRecord> computeScore(@PathVariable Long studentAId,
                                                                   @PathVariable Long studentBId) {
        return ResponseEntity.ok(service.computeScore(studentAId, studentBId));
    }
    
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<CompatibilityScoreRecord>> getScoresForStudent(@PathVariable Long studentId) {
        return ResponseEntity.ok(service.getScoresForStudent(studentId));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<CompatibilityScoreRecord> getScoreById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getScoreById(id));
    }
    
    @GetMapping
    public ResponseEntity<List<CompatibilityScoreRecord>> getAllScores() {
        return ResponseEntity.ok(service.getAllScores());
    }
}
