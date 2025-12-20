package com.example.demo.service.impl;

import com.example.demo.dto.HabitProfileDto;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.*;
import com.example.demo.repository.HabitProfileRepository;
import com.example.demo.repository.StudentProfileRepository;
import com.example.demo.service.HabitProfileService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HabitProfileServiceImpl implements HabitProfileService {
    private final HabitProfileRepository repository;
    private final StudentProfileRepository studentRepository;
    
    public HabitProfileServiceImpl(HabitProfileRepository repository, 
                                   StudentProfileRepository studentRepository) {
        this.repository = repository;
        this.studentRepository = studentRepository;
    }
    
    @Override
    public HabitProfile createOrUpdateHabit(HabitProfileDto dto) {
        if (!studentRepository.existsById(dto.getStudentId())) {
            throw new ResourceNotFoundException("Student not found");
        }
        
        if (dto.getStudyHoursPerDay() < 0) {
            throw new IllegalArgumentException("study hours must be >= 0");
        }
        
        var existing = repository.findByStudentId(dto.getStudentId());
        
        HabitProfile profile;
        if (existing.isPresent()) {
            profile = existing.get();
        } else {
            profile = new HabitProfile();
            profile.setStudentId(dto.getStudentId());
        }
        
        profile.setSleepSchedule(SleepSchedule.valueOf(dto.getSleepSchedule()));
        profile.setStudyHoursPerDay(dto.getStudyHoursPerDay());
        profile.setCleanlinessLevel(CleanlinessLevel.valueOf(dto.getCleanlinessLevel()));
        profile.setNoiseTolerance(NoiseTolerance.valueOf(dto.getNoiseTolerance()));
        profile.setSocialPreference(SocialPreference.valueOf(dto.getSocialPreference()));
        
        return repository.save(profile);
    }
    
    @Override
    public HabitProfile getHabitByStudent(Long studentId) {
        return repository.findByStudentId(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Habit not found"));
    }
    
    @Override
    public List<HabitProfile> getAllHabitProfiles() {
        return repository.findAll();
    }
    
    @Override
    public HabitProfile getHabitById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Habit not found"));
    }
}