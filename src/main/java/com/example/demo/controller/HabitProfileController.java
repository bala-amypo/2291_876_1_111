package com.example.demo.controller;

import com.example.demo.dto.HabitProfileDto;
import com.example.demo.model.HabitProfile;
import com.example.demo.service.HabitProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/habits")
@Tag(name = "Habit Profiles", description = "Habit profile management")
public class HabitProfileController {

    private final HabitProfileService habitProfileService;

    public HabitProfileController(HabitProfileService habitProfileService) {
        this.habitProfileService = habitProfileService;
    }

    @PostMapping
    @Operation(summary = "Create or update habit profile")
    public ResponseEntity<HabitProfile> createOrUpdateHabit(@RequestBody HabitProfile habit) {
        return ResponseEntity.ok(habitProfileService.createOrUpdateHabit(habit));
    }

    // @PostMapping("/{studentId}")
    // @Operation(summary = "Create or update habit profile for student")
    // public ResponseEntity<HabitProfile> createOrUpdate(@PathVariable Long studentId, 
    //                                                    @RequestBody HabitProfileDto dto) {
    //     return ResponseEntity.ok(habitProfileService.createOrUpdate(studentId, dto));
    // }

    @GetMapping("/student/{studentId}")
    @Operation(summary = "Get habit profile for student")
    public ResponseEntity<HabitProfile> getByStudent(@PathVariable Long studentId) {
        return ResponseEntity.ok(habitProfileService.getForStudent(studentId));
    }

    // @GetMapping("/{id}")
    // @Operation(summary = "Get habit profile by ID")
    // public ResponseEntity<HabitProfile> getById(@PathVariable Long id) {
    //     return ResponseEntity.ok(habitProfileService.getHabitById(id));
    // }

    // @GetMapping
    // @Operation(summary = "Get all habit profiles")
    // public ResponseEntity<List<HabitProfile>> getAll() {
    //     return ResponseEntity.ok(habitProfileService.getAllHabitProfiles());
    // }
}