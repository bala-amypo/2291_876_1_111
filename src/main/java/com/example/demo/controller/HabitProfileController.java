package com.example.demo.controller;

import com.example.demo.dto.HabitProfileDto;
import com.example.demo.model.HabitProfile;
import com.example.demo.service.HabitProfileService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/habits")
@Tag(name = "Habit Profiles", description = "Habit profile management")
@RequiredArgsConstructor
public class HabitProfileController {
    private final HabitProfileService habitProfileService;

    @PostMapping
    public ResponseEntity<HabitProfile> createOrUpdateHabit(@RequestBody HabitProfileDto dto) {
        HabitProfile habit = new HabitProfile();
        habit.setStudentId(dto.getStudentId());
        habit.setSleepSchedule(dto.getSleepSchedule());
        habit.setStudyHoursPerDay(dto.getStudyHoursPerDay());
        habit.setCleanlinessLevel(dto.getCleanlinessLevel());
        habit.setNoiseTolerance(dto.getNoiseTolerance());
        habit.setSocialPreference(dto.getSocialPreference());
        return ResponseEntity.ok(habitProfileService.createOrUpdateHabit(habit));
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<HabitProfile> getHabitByStudent(@PathVariable Long studentId) {
        return habitProfileService.getHabitByStudent(studentId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HabitProfile> getHabitById(@PathVariable Long id) {
        return ResponseEntity.ok(habitProfileService.getHabitById(id));
    }

    @GetMapping
    public ResponseEntity<List<HabitProfile>> getAllHabitProfiles() {
        return ResponseEntity.ok(habitProfileService.getAllHabitProfiles());
    }
}
