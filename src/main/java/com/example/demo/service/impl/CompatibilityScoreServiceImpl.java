package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.CompatibilityScoreRecord;
import com.example.demo.model.CompatibilityScoreRecord.CompatibilityLevel;
import com.example.demo.model.HabitProfile;
import com.example.demo.repository.CompatibilityScoreRecordRepository;
import com.example.demo.repository.HabitProfileRepository;
import com.example.demo.service.CompatibilityScoreService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CompatibilityScoreServiceImpl implements CompatibilityScoreService {

    private final CompatibilityScoreRecordRepository scoreRepo;
    private final HabitProfileRepository habitRepo;

    public CompatibilityScoreServiceImpl(
            CompatibilityScoreRecordRepository scoreRepo,
            HabitProfileRepository habitRepo) {
        this.scoreRepo = scoreRepo;
        this.habitRepo = habitRepo;
    }

    @Override
    public CompatibilityScoreRecord computeScore(Long studentAId, Long studentBId) {

        // 1️⃣ Prevent same student comparison
        if (studentAId.equals(studentBId)) {
            throw new IllegalArgumentException("Same student IDs are not allowed");
        }

        // 2️⃣ Check if compatibility already exists (both directions)
        Optional<CompatibilityScoreRecord> existing =
                scoreRepo.findByStudentAIdAndStudentBId(studentAId, studentBId);

        if (existing.isPresent()) {
            return existing.get();
        }

        // 3️⃣ Fetch habit profiles
        HabitProfile h1 = habitRepo.findByStudentId(studentAId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Habit profile not found for student " + studentAId));

        HabitProfile h2 = habitRepo.findByStudentId(studentBId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Habit profile not found for student " + studentBId));

        // 4️⃣ Compute score (MAX = 100)
        int score = 0;

        if (h1.getSleepSchedule() == h2.getSleepSchedule()) score += 20;

        if (Math.abs(h1.getStudyHoursPerDay() - h2.getStudyHoursPerDay()) <= 1) score += 20;

        if (h1.getCleanlinessLevel() == h2.getCleanlinessLevel()) score += 20;

        if (h1.getNoiseTolerance() == h2.getNoiseTolerance()) score += 20;

        if (h1.getSocialPreference() == h2.getSocialPreference()) score += 20;

        // 5️⃣ Determine ENUM compatibility level
        CompatibilityLevel level;
        if (score >= 80) {
            level = CompatibilityLevel.EXCELLENT;
        } else if (score >= 60) {
            level = CompatibilityLevel.HIGH;
        } else if (score >= 40) {
            level = CompatibilityLevel.MEDIUM;
        } else {
            level = CompatibilityLevel.LOW;
        }

        // 6️⃣ Build details JSON
        String detailsJson = String.format(
                "{ \"sleep\":\"%s\", \"studyHours\":\"%s\", \"cleanliness\":\"%s\", \"noise\":\"%s\", \"social\":\"%s\" }",
                h1.getSleepSchedule() == h2.getSleepSchedule() ? "match" : "mismatch",
                Math.abs(h1.getStudyHoursPerDay() - h2.getStudyHoursPerDay()) <= 1 ? "close" : "far",
                h1.getCleanlinessLevel() == h2.getCleanlinessLevel() ? "match" : "mismatch",
                h1.getNoiseTolerance() == h2.getNoiseTolerance() ? "match" : "mismatch",
                h1.getSocialPreference() == h2.getSocialPreference() ? "match" : "mismatch"
        );

        // 7️⃣ Save compatibility record
        CompatibilityScoreRecord record = new CompatibilityScoreRecord();
        record.setStudentAId(studentAId);
        record.setStudentBId(studentBId);
        record.setScore((double) score);
        record.setCompatibilityLevel(level);
        record.setComputedAt(LocalDateTime.now());
        record.setDetailsJson(detailsJson);

        return scoreRepo.save(record);
    }

    @Override
    public CompatibilityScoreRecord getScoreById(Long id) {
        return scoreRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Compatibility score not found with ID " + id));
    }

    @Override
    public List<CompatibilityScoreRecord> getScoresForStudent(Long studentId) {
        return scoreRepo.findByStudentAIdOrStudentBId(studentId, studentId);
    }

    @Override
    public List<CompatibilityScoreRecord> getAllScores() {
        return scoreRepo.findAll();
    }
}
