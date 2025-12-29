package com.example.demo.service.impl;

import com.example.demo.model.CompatibilityScoreRecord;
import com.example.demo.model.HabitProfile;
import com.example.demo.repository.CompatibilityScoreRecordRepository;
import com.example.demo.repository.HabitProfileRepository;
import com.example.demo.service.CompatibilityScoreService;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
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
    public CompatibilityScoreRecord computeScore(Long a, Long b) {

        if (a.equals(b))
            throw new IllegalArgumentException("same student");

        HabitProfile ha = habitRepo.findByStudentId(a)
                .orElseThrow(() -> new RuntimeException("not found"));
        HabitProfile hb = habitRepo.findByStudentId(b)
                .orElseThrow(() -> new RuntimeException("not found"));

        // 🔢 Score calculation (unchanged)
        double score = 50 + Math.min(
                ha.getStudyHoursPerDay(),
                hb.getStudyHoursPerDay()
        ) * 5;

        score = Math.min(100, Math.max(0, score));

        CompatibilityScoreRecord rec =
                scoreRepo.findByStudentAIdAndStudentBId(a, b)
                        .orElse(new CompatibilityScoreRecord());

        rec.setStudentAId(a);
        rec.setStudentBId(b);
        rec.setScore(score);
        rec.setComputedAt(LocalDateTime.now());

        // ✅ FIX 1: Set compatibility level
        rec.setCompatibilityLevel(determineLevel(score));

        // ✅ FIX 2: Set detailsJson (simple + safe)
        rec.setDetailsJson(
                String.format(
                        "{\"studentAStudyHours\":%d,\"studentBStudyHours\":%d}",
                        ha.getStudyHoursPerDay(),
                        hb.getStudyHoursPerDay()
                )
        );

        return scoreRepo.save(rec);
    }

   
    private CompatibilityScoreRecord.CompatibilityLevel determineLevel(double score) {
        if (score >= 90) return CompatibilityScoreRecord.CompatibilityLevel.EXCELLENT;
        if (score >= 75) return CompatibilityScoreRecord.CompatibilityLevel.HIGH;
        if (score >= 50) return CompatibilityScoreRecord.CompatibilityLevel.MEDIUM;
        return CompatibilityScoreRecord.CompatibilityLevel.LOW;
    }

    @Override
    public List<CompatibilityScoreRecord> getScoresForStudent(Long id) {
        return scoreRepo.findByStudentAIdOrStudentBId(id, id);
    }

    @Override
    public CompatibilityScoreRecord getScoreById(Long id) {
        return scoreRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("not found"));
    }

    @Override
    public List<CompatibilityScoreRecord> getAllScores() {
        return scoreRepo.findAll();
    }
}