package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.*;
import com.example.demo.repository.CompatibilityScoreRecordRepository;
import com.example.demo.repository.HabitProfileRepository;
import com.example.demo.service.CompatibilityScoreService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CompatibilityScoreServiceImpl implements CompatibilityScoreService {

    private final CompatibilityScoreRecordRepository scoreRepo;
    private final HabitProfileRepository habitRepo;

    public CompatibilityScoreServiceImpl(CompatibilityScoreRecordRepository scoreRepo,
                                         HabitProfileRepository habitRepo) {
        this.scoreRepo = scoreRepo;
        this.habitRepo = habitRepo;
    }

    @Override
    public CompatibilityScoreRecord computeScore(Long studentAId, Long studentBId) {

        if (studentAId.equals(studentBId)) {
            throw new IllegalArgumentException("Student A and Student B must be different");
        }

        HabitProfile habitA = habitRepo.findByStudentId(studentAId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Habit profile not found for student A"));

        HabitProfile habitB = habitRepo.findByStudentId(studentBId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Habit profile not found for student B"));

        double score = calculateCompatibility(habitA, habitB);

        var existing = scoreRepo.findByStudentAIdAndStudentBId(studentAId, studentBId);

        CompatibilityScoreRecord record;
        if (existing.isPresent()) {
            record = existing.get();
            record.setScore(score);
            record.setCompatibilityLevel(determineLevel(score)); // 🔧 FIX
        } else {
            record = CompatibilityScoreRecord.builder()
                    .studentAId(studentAId)
                    .studentBId(studentBId)
                    .score(score)
                    .compatibilityLevel(determineLevel(score))
                    .build();
        }

        return scoreRepo.save(record);
    }

    private double calculateCompatibility(HabitProfile a, HabitProfile b) {
        double score = 100.0;

        if (!Objects.equals(a.getSleepSchedule(), b.getSleepSchedule())) {
            score -= 20;
        }

        if (!Objects.equals(a.getCleanlinessLevel(), b.getCleanlinessLevel())) {
            score -= 15;
        }

        if (!Objects.equals(a.getNoiseTolerance(), b.getNoiseTolerance())) {
            score -= 15;
        }

        if (!Objects.equals(a.getSocialPreference(), b.getSocialPreference())) {
            score -= 10;
        }

        int studyDiff = Math.abs(a.getStudyHoursPerDay() - b.getStudyHoursPerDay());
        if (studyDiff > 3) {
            score -= 10;
        }

        return Math.max(0, Math.min(100, score));
    }

    public CompatibilityLevel determineLevel(double score) {
        if (score >= 80) return CompatibilityLevel.EXCELLENT;
        if (score >= 60) return CompatibilityLevel.HIGH;
        if (score >= 40) return CompatibilityLevel.MEDIUM;
        return CompatibilityLevel.LOW;
    }

    @Override
    public CompatibilityScoreRecord getScoreById(Long id) {
        return scoreRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Score not found"));
    }

    @Override
    public List<CompatibilityScoreRecord> getScoresForStudent(Long studentId) {
        return scoreRepo.findByStudentAIdOrStudentBIdOrderByScoreDesc(studentId, studentId);
    }

    @Override
    public List<CompatibilityScoreRecord> getAllScores() {
        return scoreRepo.findAll();
    }
}
