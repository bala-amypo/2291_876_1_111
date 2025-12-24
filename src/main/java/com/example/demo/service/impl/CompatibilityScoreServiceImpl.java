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

    private final CompatibilityScoreRecordRepository scoreRepo;
    private final HabitProfileRepository habitRepo;

    public CompatibilityScoreServiceImpl(
            CompatibilityScoreRecordRepository scoreRepo,
            HabitProfileRepository habitRepo) {
        this.scoreRepo = scoreRepo;
        this.habitRepo = habitRepo;
    }

    public CompatibilityScoreRecord computeScore(Long a, Long b) {

        if (a.equals(b))
            throw new IllegalArgumentException("same student");

        HabitProfile ha = habitRepo.findByStudentId(a)
                .orElseThrow(() -> new RuntimeException("not found"));
        HabitProfile hb = habitRepo.findByStudentId(b)
                .orElseThrow(() -> new RuntimeException("not found"));

        double score = 50 + Math.min(ha.getStudyHoursPerDay(), hb.getStudyHoursPerDay()) * 5;
        score = Math.min(100, Math.max(0, score));

        CompatibilityScoreRecord rec =
                scoreRepo.findByStudentAIdAndStudentBId(a, b)
                        .orElse(new CompatibilityScoreRecord());

        rec.setStudentAId(a);
        rec.setStudentBId(b);
        rec.setScore(score);
        rec.setComputedAt(java.time.LocalDateTime.now());

        return scoreRepo.save(rec);
    }

    public List<CompatibilityScoreRecord> getScoresForStudent(Long id) {
        return scoreRepo.findByStudentAIdOrStudentBId(id, id);
    }

    public CompatibilityScoreRecord getScoreById(Long id) {
        return scoreRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("not found"));
    }

    public List<CompatibilityScoreRecord> getAllScores() {
        return scoreRepo.findAll();
    }
}
