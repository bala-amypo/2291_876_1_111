package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.*;
import com.example.demo.repository.CompatibilityScoreRecordRepository;
import com.example.demo.repository.HabitProfileRepository;
import com.example.demo.service.CompatibilityScoreService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CompatibilityScoreServiceImpl implements CompatibilityScoreService {
    private final CompatibilityScoreRecordRepository scoreRepository;
    private final HabitProfileRepository habitRepository;

    public CompatibilityScoreServiceImpl(CompatibilityScoreRecordRepository scoreRepository, 
                                       HabitProfileRepository habitRepository) {
        this.scoreRepository = scoreRepository;
        this.habitRepository = habitRepository;
    }

    @Override
    public CompatibilityScoreRecord computeScore(Long studentAId, Long studentBId) {
        if (studentAId.equals(studentBId)) {
            throw new IllegalArgumentException("same student");
        }

        HabitProfile habitA = habitRepository.findByStudentId(studentAId)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));
        HabitProfile habitB = habitRepository.findByStudentId(studentBId)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));

        double score = calculateCompatibilityScore(habitA, habitB);
        CompatibilityLevel level = determineLevel(score);

        CompatibilityScoreRecord record = new CompatibilityScoreRecord();
        record.setStudentAId(Math.min(studentAId, studentBId));
        record.setStudentBId(Math.max(studentAId, studentBId));
        record.setScore(score);
        record.setCompatibilityLevel(level);
        record.setComputedAt(LocalDateTime.now());
        record.setDetailsJson("{}");

        return scoreRepository.save(record);
    }

    private double calculateCompatibilityScore(HabitProfile a, HabitProfile b) {
        double score = 0.0;
        // Simple scoring logic - tests expect deterministic results
        if (a.getSleepSchedule() == b.getSleepSchedule()) score += 25;
        if (a.getCleanlinessLevel() == b.getCleanlinessLevel()) score += 25;
        if (a.getNoiseTolerance() == b.getNoiseTolerance()) score += 25;
        if (a.getSocialPreference() == b.getSocialPreference()) score += 25;
        return Math.min(100, score);
    }

    private CompatibilityLevel determineLevel(double score) {
        if (score >= 90) return CompatibilityLevel.EXCELLENT;
        if (score >= 70) return CompatibilityLevel.HIGH;
        if (score >= 50) return CompatibilityLevel.MEDIUM;
        return CompatibilityLevel.LOW;
    }

    @Override
    public CompatibilityScoreRecord getScoreById(Long id) {
        return scoreRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));
    }

    @Override
    public List<CompatibilityScoreRecord> getScoresForStudent(Long studentId) {
        return scoreRepository.findByStudentAIdOrStudentBId(studentId, studentId);
    }

    @Override
    public List<CompatibilityScoreRecord> getAllScores() {
        return scoreRepository.findAll();
    }
}
