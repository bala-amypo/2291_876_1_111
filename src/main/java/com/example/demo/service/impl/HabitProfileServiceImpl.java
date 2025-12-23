package com.example.demo.service.impl;

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

    @Override
    public HabitProfile createHabitProfile(
            Long studentId,
            Boolean smoking,
            Boolean drinking,
            String cleanlinessLevel,
            String noiseTolerance,
            String sleepSchedule,
            String socialPreference,
            Integer studyHoursPerDay
    ) {
        HabitProfile habit = new HabitProfile();

        habit.setStudentId(studentId);
        habit.setSmoking(smoking);
        habit.setDrinking(drinking);
        habit.setStudyHoursPerDay(studyHoursPerDay);

        habit.setCleanlinessLevel(
                HabitProfile.CleanlinessLevel.valueOf(cleanlinessLevel)
        );
        habit.setNoiseTolerance(
                HabitProfile.NoiseTolerance.valueOf(noiseTolerance)
        );
        habit.setSleepSchedule(
                HabitProfile.SleepSchedule.valueOf(sleepSchedule)
        );
        habit.setSocialPreference(
                HabitProfile.SocialPreference.valueOf(socialPreference)
        );

        return habitProfileRepository.save(habit);
    }

    // ✅ THIS METHOD WAS MISSING (CAUSE OF YOUR ERROR)
    @Override
    public HabitProfile getHabitById(Long id) {
        return habitProfileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Habit profile not found"));
    }

    // ✅ Add ONLY if interface declares it
    @Override
    public List<HabitProfile> getAllHabits() {
        return habitProfileRepository.findAll();
    }
}
