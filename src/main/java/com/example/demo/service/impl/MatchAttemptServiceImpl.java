package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.MatchAttemptRecord;
import com.example.demo.repository.CompatibilityScoreRecordRepository;
import com.example.demo.repository.MatchAttemptRecordRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import com.example.demo.service.MatchAttemptService;

@Service
public class MatchAttemptServiceImpl implements MatchAttemptService {

    private final MatchAttemptRecordRepository attemptRepository;
    
    private final CompatibilityScoreRecordRepository scoreRepository;

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
public MatchAttemptRecord getAttemptById(Long id) {
    return attemptRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Attempt not found"));
}

    @Override
    public List<MatchAttemptRecord> getAttemptsByStudent(Long studentId) {
        return attemptRepository.findByInitiatorStudentIdOrCandidateStudentId(studentId, studentId);
    }

    @Override
public MatchAttemptRecord updateAttemptStatus(
        Long attemptId,
        MatchAttemptRecord.Status status
) {
    MatchAttemptRecord attempt = attemptRepository.findById(attemptId)
            .orElseThrow(() -> new ResourceNotFoundException("Attempt not found"));

    attempt.setStatus(status);
    return attemptRepository.save(attempt);
}

    @Override
    public List<MatchAttemptRecord> getAllMatchAttempts() {
        return attemptRepository.findAll();
    }
}