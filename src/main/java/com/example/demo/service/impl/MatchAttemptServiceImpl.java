package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.MatchAttemptRecord;
import com.example.demo.repository.CompatibilityScoreRecordRepository;
import com.example.demo.repository.MatchAttemptRecordRepository;
import com.example.demo.service.MatchAttemptService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchAttemptServiceImpl implements MatchAttemptService {
    
    private final MatchAttemptRecordRepository attemptRepository;
    private final CompatibilityScoreRecordRepository scoreRepository;

    // EXACT TEST CONSTRUCTOR
    public MatchAttemptServiceImpl(MatchAttemptRecordRepository attemptRepository,
                                 CompatibilityScoreRecordRepository scoreRepository) {
        this.attemptRepository = attemptRepository;
        this.scoreRepository = scoreRepository;
    }

    @Override
    public MatchAttemptRecord logMatchAttempt(MatchAttemptRecord attempt) {
        return attemptRepository.save(attempt);
    }

    @Override
    public List<MatchAttemptRecord> getAttemptsByStudent(Long studentId) {
        return attemptRepository.findByInitiatorStudentIdOrCandidateStudentId(studentId, studentId);
    }

    @Override
    public MatchAttemptRecord updateAttemptStatus(Long attemptId, String status) {
        MatchAttemptRecord attempt = attemptRepository.findById(attemptId)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));
        try {
            attempt.setStatus(MatchAttemptRecord.Status.valueOf(status.toUpperCase()));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid status");
        }
        return attemptRepository.save(attempt);
    }

    @Override
    public List<MatchAttemptRecord> getAllMatchAttempts() {
        return attemptRepository.findAll();
    }
}
