package com.example.demo.service;

import com.example.demo.model.HabitProfile;

import java.util.List;
import java.util.Optional;

public interface HabitProfileService {
    HabitProfile createOrUpdateHabit(HabitProfile habit);
    Optional<HabitProfile> getHabitByStudent(Long studentId);
    List<HabitProfile> getAllHabitProfiles();
    HabitProfile getHabitById(Long id);
}
