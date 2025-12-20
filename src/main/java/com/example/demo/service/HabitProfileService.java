package com.example.demo.service;

import com.example.demo.dto.HabitProfileDto;
import com.example.demo.model.HabitProfile;
import java.util.List;

public interface HabitProfileService {
    HabitProfile createOrUpdateHabit(HabitProfileDto dto);
    HabitProfile getHabitByStudent(Long studentId);
    List<HabitProfile> getAllHabitProfiles();
    HabitProfile getHabitById(Long id);
}
