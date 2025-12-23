package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.HabitProfile;
import com.example.demo.repository.HabitProfileRepository;
import com.example.demo.service.HabitProfileService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HabitProfileServiceImpl implements HabitProfileService {
    
    private final HabitProfileRepository habitProfileRepository;

    // EXACT TEST CONSTRUCTOR
    public HabitProfileServiceImpl(HabitProfileRepository habitProfileRepository) {
        this.habitProfileRepository = habitProfileRepository;
    }

    @Override
    public HabitProfile createOrUpdateHabit(HabitProfile habit) {
        if (habit.getStudyHoursPerDay() != null && habit.getStudyHoursPerDay() < 0) {
            throw new IllegalArgumentException("study hours");
        }
        habit.setUpdatedAt(java.time.LocalDateTime.now());
        return habitProfileRepository.save(habit);
    }

    @Override
    public Optional<HabitProfile> getHabitByStudent(Long studentId) {
        return habitProfileRepository.findByStudentId(studentId);
    }

    @Override
    public List<HabitProfile> getAllHabitProfiles() {
        return habitProfileRepository.findAll();
    }

    @Override
    public HabitProfile getHabitById(Long id) {
        return habitProfileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));
    }
}
