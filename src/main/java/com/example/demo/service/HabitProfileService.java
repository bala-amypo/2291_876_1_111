package com.example.demo.service;

import com.example.demo.model.HabitProfile;
import java.util.*;

public interface HabitProfileService {
    HabitProfile createOrUpdateHabit(HabitProfile h);
    HabitProfile getHabitByStudent(Long studentId);
    Optional<HabitProfile> getHabitById(Long id);
    List<HabitProfile> getAllHabitProfiles();
}
