package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.HabitProfile;
import com.example.demo.model.MatchResult;
import com.example.demo.model.StudentProfile;
import com.example.demo.repository.HabitProfileRepository;
import com.example.demo.repository.MatchResultRepository;
import com.example.demo.repository.StudentProfileRepository;
import org.springframework.stereotype.Service;
import java.time.LocalTime;
import java.util.List;
import com.example.demo.service.MatchService;

@Service
public class MatchServiceImpl implements MatchService {

    private final MatchResultRepository matchResultRepository;
    private final StudentProfileRepository studentProfileRepository;
    private final HabitProfileRepository habitProfileRepository;

    public MatchServiceImpl(MatchResultRepository matchResultRepository,
                           StudentProfileRepository studentProfileRepository,
                           HabitProfileRepository habitProfileRepository) {
        this.matchResultRepository = matchResultRepository;
        this.studentProfileRepository = studentProfileRepository;
        this.habitProfileRepository = habitProfileRepository;
    }

    @Override
    public MatchResult computeMatch(Long studentAId, Long studentBId) {
        if (studentAId.equals(studentBId)) {
            throw new IllegalArgumentException("Cannot match same student");
        }

        StudentProfile studentA = studentProfileRepository.findById(studentAId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
        StudentProfile studentB = studentProfileRepository.findById(studentBId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));

        HabitProfile habitA = habitProfileRepository.findByStudentId(studentAId)
                .orElseThrow(() -> new ResourceNotFoundException("Profile not found"));
        HabitProfile habitB = habitProfileRepository.findByStudentId(studentBId)
                .orElseThrow(() -> new ResourceNotFoundException("Profile not found"));

        double score = calculateCompatibilityScore(habitA, habitB);

        if (score < 0 || score > 100) {
            throw new IllegalArgumentException("Score must be between 0-100");
        }

        MatchResult result = new MatchResult();
        result.setStudentA(studentA);
        result.setStudentB(studentB);
        result.setScore(score);
        result.setReasonSummary(generateReasonSummary(habitA, habitB, score));

        return matchResultRepository.save(result);
    }

    @Override
    public List<MatchResult> getMatchesFor(Long studentId) {
        return matchResultRepository.findByStudentAIdOrStudentBIdOrderByScoreDesc(studentId, studentId);
    }

    @Override
    public MatchResult getById(Long id) {
        return matchResultRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Match not found"));
    }

    private double calculateCompatibilityScore(HabitProfile habitA, HabitProfile habitB) {
        double totalScore = 0;
        int factors = 0;

        // Sleep schedule compatibility
        if (habitA.getSleepTime() != null && habitB.getSleepTime() != null) {
            totalScore += calculateTimeCompatibility(habitA.getSleepTime(), habitB.getSleepTime());
            factors++;
        }

        // Wake time compatibility
        if (habitA.getWakeTime() != null && habitB.getWakeTime() != null) {
            totalScore += calculateTimeCompatibility(habitA.getWakeTime(), habitB.getWakeTime());
            factors++;
        }

        // Smoking compatibility
        if (habitA.getSmoking() != null && habitB.getSmoking() != null) {
            totalScore += (habitA.getSmoking().equals(habitB.getSmoking()) ? 100 : 0);
            factors++;
        }

        // Drinking compatibility
        if (habitA.getDrinking() != null && habitB.getDrinking() != null) {
            totalScore += (habitA.getDrinking().equals(habitB.getDrinking()) ? 100 : 0);
            factors++;
        }

        // Cleanliness level compatibility
        if (habitA.getCleanlinessLevel() != null && habitB.getCleanlinessLevel() != null) {
            totalScore += (habitA.getCleanlinessLevel().equals(habitB.getCleanlinessLevel()) ? 100 : 50);
            factors++;
        }

        // Noise preference compatibility
        if (habitA.getNoisePreference() != null && habitB.getNoisePreference() != null) {
            totalScore += (habitA.getNoisePreference().equals(habitB.getNoisePreference()) ? 100 : 50);
            factors++;
        }

        // Study style compatibility
        if (habitA.getStudyStyle() != null && habitB.getStudyStyle() != null) {
            totalScore += (habitA.getStudyStyle().equals(habitB.getStudyStyle()) ? 100 : 70);
            factors++;
        }

        // Social preference compatibility
        if (habitA.getSocialPreference() != null && habitB.getSocialPreference() != null) {
            totalScore += (habitA.getSocialPreference().equals(habitB.getSocialPreference()) ? 100 : 60);
            factors++;
        }

        return factors > 0 ? totalScore / factors : 50.0;
    }

    private double calculateTimeCompatibility(LocalTime time1, LocalTime time2) {
        long diff = Math.abs(time1.toSecondOfDay() - time2.toSecondOfDay());
        long maxDiff = 12 * 3600; // 12 hours in seconds
        return 100 * (1 - Math.min(diff, maxDiff) / (double) maxDiff);
    }

    private String generateReasonSummary(HabitProfile habitA, HabitProfile habitB, double score) {
        StringBuilder summary = new StringBuilder();
        summary.append("Compatibility Score: ").append(String.format("%.1f", score)).append("%. ");

        if (habitA.getSmoking() != null && habitB.getSmoking() != null && habitA.getSmoking().equals(habitB.getSmoking())) {
            summary.append("Both have same smoking preference. ");
        }

        if (habitA.getDrinking() != null && habitB.getDrinking() != null && habitA.getDrinking().equals(habitB.getDrinking())) {
            summary.append("Both have same drinking preference. ");
        }

        if (habitA.getCleanlinessLevel() != null && habitB.getCleanlinessLevel() != null && 
            habitA.getCleanlinessLevel().equals(habitB.getCleanlinessLevel())) {
            summary.append("Similar cleanliness standards. ");
        }

        if (habitA.getSocialPreference() != null && habitB.getSocialPreference() != null && 
            habitA.getSocialPreference().equals(habitB.getSocialPreference())) {
            summary.append("Compatible social preferences. ");
        }

        return summary.toString();
    }
}