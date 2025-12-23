package com.example.demo.controller;

import com.example.demo.model.MatchResult;
import com.example.demo.service.MatchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/matches")
@Tag(name = "Matches", description = "Compatibility matching")
public class MatchController {

    private final MatchService matchService;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @PostMapping("/compute")
    @Operation(summary = "Compute compatibility match between two students")
    public ResponseEntity<MatchResult> computeMatch(@RequestBody Map<String, Long> request) {
        Long studentAId = request.get("studentAId");
        Long studentBId = request.get("studentBId");
        return ResponseEntity.ok(matchService.computeMatch(studentAId, studentBId));
    }

    @GetMapping("/student/{studentId}")
    @Operation(summary = "Get all matches for a student")
    public ResponseEntity<List<MatchResult>> getMatchesForStudent(@PathVariable Long studentId) {
        return ResponseEntity.ok(matchService.getMatchesFor(studentId));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get match by ID")
    public ResponseEntity<MatchResult> getMatch(@PathVariable Long id) {
        return ResponseEntity.ok(matchService.getById(id));
    }
}