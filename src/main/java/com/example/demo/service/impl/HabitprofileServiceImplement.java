package com.example.demo.service.impl;

import org.springframework.stereotype.Service;

import com.example.demo.entity.HabitProfile;
import com.example.demo.exception.InvalidStudyHoursException;
import com.example.demo.repository.HabitProfileRepository;
import com.example.demo.service.HabitProfileService;

@Service
public class HabitProfileServiceImplement implements HabitProfileService {

    private final HabitProfileRepository repository;

    public HabitProfileServiceImpl(HabitProfileRepository repository) {
        this.repository = repository;
    }

    @Override
    public HabitProfile saveHabitProfile(HabitProfile habitProfile) {

        if (habitProfile.getStudyHoursPerDay() == null
                || habitProfile.getStudyHoursPerDay() < 0) {
            throw new InvalidStudyHoursException("study hours invalid");
        }

        return repository.save(habitProfile);
    }
}
