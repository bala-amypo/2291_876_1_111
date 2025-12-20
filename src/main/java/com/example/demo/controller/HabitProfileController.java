package com.example.demo.controller;

import com.example.demo.dto.HabitProfileDto;
import com.example.demo.model.HabitProfile;
import com.example.demo.service.HabitProfileService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/habits")
@Tag(name = "Habit Profiles", description = "Habit profile management")
public class HabitProfileController {
    private final HabitProfileService service;
    
    public HabitProfileController(HabitProfileService service) {
        this.service = service;
    }
    
    @PostMapping
    public ResponseEntity<HabitProfile> createOrUpdateHabit(@RequestBody HabitProfileDto dto) {
        return ResponseEntity.ok(service.createOrUpdateHabit(dto));
    }
    
    @GetMapping("/student/{studentId}")
    public ResponseEntity<HabitProfile> getHabitByStudent(@PathVariable Long studentId) {
        return ResponseEntity.ok(service.getHabitByStudent(studentId));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<HabitProfile> getHabitById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getHabitById(id));
    }
    
    @GetMapping
    public ResponseEntity<List<HabitProfile>> getAllHabits() {
        return ResponseEntity.ok(service.getAllHabitProfiles());
    }
}
