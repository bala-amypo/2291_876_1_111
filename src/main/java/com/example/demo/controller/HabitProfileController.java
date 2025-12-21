package com.example.demo.controller;

import com.example.demo.model.HabitProfile;
import com.example.demo.service.HabitProfileService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/habits")
public class HabitProfileController {

    private final HabitProfileService service;

    public HabitProfileController(HabitProfileService service) {
        this.service = service;
    }

    @PostMapping
    public HabitProfile createOrUpdate(@RequestBody HabitProfile h) {
        return service.createOrUpdateHabit(h);
    }

    @GetMapping("/student/{studentId}")
    public HabitProfile byStudent(@PathVariable Long studentId) {
        return service.getHabitByStudent(studentId);
    }

    @GetMapping("/{id}")
    public HabitProfile byId(@PathVariable Long id) {
        return service.getHabitById(id);
    }

    @GetMapping
    public List<HabitProfile> getAll() {
        return service.getAllHabitProfiles();
    }
}