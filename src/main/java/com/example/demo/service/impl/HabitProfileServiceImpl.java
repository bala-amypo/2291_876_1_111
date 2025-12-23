package com.example.demo.service.impl;

import com.example.demo.dto.HabitProfileDto;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.HabitProfile;
import com.example.demo.repository.HabitProfileRepository;
import com.example.demo.service.HabitProfileService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HabitProfileServiceImpl implements HabitProfileService {

    private final HabitProfileRepository habitProfileRepository;

    public HabitProfileServiceImpl(HabitProfileRepository habitProfileRepository) {
        this.habitProfileRepository = habitProfileRepository;
    }

    // 1️⃣ REQUIRED
    @Override
    public HabitProfile createOrUpdate(Long studentId, HabitProfileDto dto) {
        HabitProfile habit = habitProfileRepository
                .findByStudentId(studentId)
                .orElse(new HabitProfile());

        habit.setStudentId(studentId);
        habit.setSmoking(dto.getSmoking());
        habit.setDrinking(dto.getDrinking());
        habit.setStudyHoursPerDay(dto.getStudyHoursPerDay());

        habit.setCleanlinessLevel(
                HabitProfile.CleanlinessLevel.valueOf(dto.getCleanlinessLevel())
        );
        habit.setNoiseTolerance(
                HabitProfile.NoiseTolerance.valueOf(dto.getNoiseTolerance())
        );
        habit.setSleepSchedule(
                HabitProfile.SleepSchedule.valueOf(dto.getSleepSchedule())
        );
        habit.setSocialPreference(
                HabitProfile.SocialPreference.valueOf(dto.getSocialPreference())
        );

        habit.setSleepTime(dto.getSleepTime());
        habit.setWakeTime(dto.getWakeTime());

        return habitProfileRepository.save(habit);
    }

    // 2️⃣ REQUIRED
    @Override
    public HabitProfile createOrUpdateHabit(HabitProfile habit) {
        return habitProfileRepository.save(habit);
    }

    // 3️⃣ REQUIRED
    @Override
    public HabitProfile getForStudent(Long studentId) {
        return habitProfileRepository.findByStudentId(studentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Habit profile not found for student")
                );
    }

    // 4️⃣ REQUIRED (YES, BOTH METHODS MUST EXIST)
    @Override
    public HabitProfile getHabitByStudent(Long studentId) {
        return habitProfileRepository.findByStudentId(studentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Habit profile not found for student")
                );
    }

    // 5️⃣ REQUIRED
    @Override
    public List<HabitProfile> getAllHabitProfiles() {
        return habitProfileRepository.findAll();
    }

    // 6️⃣ REQUIRED
    @Override
    public HabitProfile getHabitById(Long id) {
        return habitProfileRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Habit profile not found")
                );
    }
}
