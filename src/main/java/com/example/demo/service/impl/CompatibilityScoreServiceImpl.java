package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.CompatibilityScoreRecord;
import com.example.demo.model.HabitProfile;
import com.example.demo.repository.CompatibilityScoreRecordRepository;
import com.example.demo.repository.HabitProfileRepository;
import com.example.demo.service.CompatibilityScoreService;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompatibilityScoreServiceImpl implements CompatibilityScoreService {

    private final CompatibilityScoreRecordRepository scoreRepository;
    private final HabitProfileRepository habitRepository;

    public CompatibilityScoreServiceImpl(
            CompatibilityScoreRecordRepository scoreRepository,
            HabitProfileRepository habitRepository
    ) {
        this.scoreRepository = scoreRepository;
        this.habitRepository = habitRepository;
    }

    @Override
    public CompatibilityScoreRecord computeScore(Long studentAId, Long studentBId) {

        if (studentAId.equals(studentBId)) {
            throw new IllegalArgumentException("Cannot match same student");
        }

        HabitProfile habitA = habitRepository.findByStudentId(studentAId)
                .orElseThrow(() -> new ResourceNotFoundException("Profile not found"));

        HabitProfile habitB = habitRepository.findByStudentId(studentBId)
                .orElseThrow(() -> new ResourceNotFoundException("Profile not found"));

        double score = calculateCompatibilityScore(habitA, habitB);

        CompatibilityScoreRecord.CompatibilityLevel level =
                determineCompatibilityLevel(score);

        CompatibilityScoreRecord record = new CompatibilityScoreRecord();
        record.setStudentAId(studentAId);
        record.setStudentBId(studentBId);
        record.setScore(score);
        record.setCompatibilityLevel(level);
        record.setDetailsJson(generateDetails(habitA, habitB));

        return scoreRepository.save(record);
    }

    @Override
    public CompatibilityScoreRecord getScoreById(Long id) {
        return scoreRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Score not found"));
    }

    @Override
    public List<CompatibilityScoreRecord> getScoresForStudent(Long studentId) {
        return scoreRepository.findByStudentAIdOrStudentBId(studentId, studentId);
    }

    @Override
    public List<CompatibilityScoreRecord> getAllScores() {
        return scoreRepository.findAll();
    }

    /* ------------------ INTERNAL LOGIC ------------------ */

    private double calculateCompatibilityScore(HabitProfile habitA, HabitProfile habitB) {

        double totalScore = 0;
        int factors = 0;

        if (habitA.getSleepSchedule() != null && habitB.getSleepSchedule() != null) {
            totalScore += habitA.getSleepSchedule() == habitB.getSleepSchedule() ? 100 : 50;
            factors++;
        }

        if (habitA.getStudyHoursPerDay() != null && habitB.getStudyHoursPerDay() != null) {
            int diff = Math.abs(habitA.getStudyHoursPerDay() - habitB.getStudyHoursPerDay());
            totalScore += Math.max(0, 100 - diff * 10);
            factors++;
        }

        if (habitA.getCleanlinessLevel() != null && habitB.getCleanlinessLevel() != null) {
            totalScore += habitA.getCleanlinessLevel() == habitB.getCleanlinessLevel() ? 100 : 60;
            factors++;
        }

        if (habitA.getNoiseTolerance() != null && habitB.getNoiseTolerance() != null) {
            totalScore += habitA.getNoiseTolerance() == habitB.getNoiseTolerance() ? 100 : 60;
            factors++;
        }

        if (habitA.getSocialPreference() != null && habitB.getSocialPreference() != null) {
            totalScore += habitA.getSocialPreference() == habitB.getSocialPreference() ? 100 : 70;
            factors++;
        }

        return factors > 0 ? totalScore / factors : 50.0;
    }

    private CompatibilityScoreRecord.CompatibilityLevel determineCompatibilityLevel(double score) {
        if (score >= 85) return CompatibilityScoreRecord.CompatibilityLevel.EXCELLENT;
        if (score >= 70) return CompatibilityScoreRecord.CompatibilityLevel.HIGH;
        if (score >= 50) return CompatibilityScoreRecord.CompatibilityLevel.MEDIUM;
        return CompatibilityScoreRecord.CompatibilityLevel.LOW;
    }

    private String generateDetails(HabitProfile a, HabitProfile b) {
        return String.format(
                "{\"sleepScheduleMatch\": %b, \"cleanlinessMatch\": %b, \"noiseMatch\": %b}",
                a.getSleepSchedule() != null && a.getSleepSchedule() == b.getSleepSchedule(),
                a.getCleanlinessLevel() != null && a.getCleanlinessLevel() == b.getCleanlinessLevel(),
                a.getNoiseTolerance() != null && a.getNoiseTolerance() == b.getNoiseTolerance()
        );
    }
}
