package com.example.demo.service.impl;

import com.example.demo.dto.HabitProfileDto;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.HabitProfile;
import com.example.demo.model.StudentProfile;
import com.example.demo.repository.HabitProfileRepository;
import com.example.demo.repository.StudentProfileRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class HabitProfileServiceImpl implements HabitProfileService {

    private final HabitProfileRepository habitProfileRepository;
    private final StudentProfileRepository studentProfileRepository;

    public HabitProfileServiceImpl(HabitProfileRepository habitProfileRepository,
                                  StudentProfileRepository studentProfileRepository) {
        this.habitProfileRepository = habitProfileRepository;
        this.studentProfileRepository = studentProfileRepository;
    }

    @Override
    public HabitProfile createOrUpdate(Long studentId, HabitProfileDto dto) {
        StudentProfile student = studentProfileRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));

        Optional<HabitProfile> existing = habitProfileRepository.findByStudentId(studentId);
        
        HabitProfile habit;
        if (existing.isPresent()) {
            habit = existing.get();
        } else {
            habit = new HabitProfile();
            habit.setStudent(student);
        }

        mapDtoToEntity(dto, habit);
        validateHabit(habit);

        return habitProfileRepository.save(habit);
    }

    @Override
    public HabitProfile createOrUpdateHabit(HabitProfile habit) {
        if (habit.getStudent() == null) {
            throw new ResourceNotFoundException("Student not found");
        }

        Optional<HabitProfile> existing = habitProfileRepository.findByStudentId(habit.getStudent().getId());
        if (existing.isPresent() && !existing.get().getId().equals(habit.getId())) {
            throw new IllegalArgumentException("Student already has a habit profile");
        }

        validateHabit(habit);
        return habitProfileRepository.save(habit);
    }

    @Override
    public HabitProfile getForStudent(Long studentId) {
        return habitProfileRepository.findByStudentId(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Profile not found"));
    }

    @Override
    public HabitProfile getHabitByStudent(Long studentId) {
        return habitProfileRepository.findByStudentId(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Profile not found"));
    }

    @Override
    public List<HabitProfile> getAllHabitProfiles() {
        return habitProfileRepository.findAll();
    }

    @Override
    public HabitProfile getHabitById(Long id) {
        return habitProfileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Profile not found"));
    }

    private void mapDtoToEntity(HabitProfileDto dto, HabitProfile habit) {
        if (dto.getSmoking() != null) habit.setSmoking(dto.getSmoking());
        if (dto.getDrinking() != null) habit.setDrinking(dto.getDrinking());
        if (dto.getSleepTime() != null) habit.setSleepTime(dto.getSleepTime());
        if (dto.getWakeTime() != null) habit.setWakeTime(dto.getWakeTime());
        if (dto.getCleanlinessLevel() != null) habit.setCleanlinessLevel(dto.getCleanlinessLevel());
        if (dto.getNoisePreference() != null) habit.setNoisePreference(dto.getNoisePreference());
        if (dto.getStudyStyle() != null) habit.setStudyStyle(dto.getStudyStyle());
        if (dto.getSocialPreference() != null) habit.setSocialPreference(dto.getSocialPreference());
        if (dto.getVisitorsFrequency() != null) habit.setVisitorsFrequency(dto.getVisitorsFrequency());
        if (dto.getSleepSchedule() != null) habit.setSleepSchedule(dto.getSleepSchedule());
        if (dto.getStudyHoursPerDay() != null) habit.setStudyHoursPerDay(dto.getStudyHoursPerDay());
        if (dto.getNoiseTolerance() != null) habit.setNoiseTolerance(dto.getNoiseTolerance());
    }

    private void validateHabit(HabitProfile habit) {
        if (habit.getStudyHoursPerDay() != null && habit.getStudyHoursPerDay() < 0) {
            throw new IllegalArgumentException("study hours must be >= 0");
        }

        if (habit.getCleanlinessLevel() != null) {
            String level = habit.getCleanlinessLevel().toUpperCase();
            if (!level.equals("LOW") && !level.equals("MEDIUM") && !level.equals("HIGH")) {
                throw new IllegalArgumentException("Cleanliness level must be in range 1-5");
            }
        }

        if (habit.getNoisePreference() != null) {
            String pref = habit.getNoisePreference().toUpperCase();
            if (!pref.equals("LOW") && !pref.equals("MEDIUM") && !pref.equals("HIGH")) {
                throw new IllegalArgumentException("Noise preference must be in range 1-5");
            }
        }
    }
}