package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.HabitProfile;
import com.example.demo.service.HabitProfileService;

@RestController
@RequestMapping("/habits")
public class HabitProfileController {

    private final HabitProfileService service;

    public HabitProfileController(HabitProfileService service) {
        this.service = service;
    }

    @PostMapping
    public HabitProfile saveHabit(@RequestBody HabitProfile habitProfile) {
        return service.saveHabitProfile(habitProfile);
    }
}
