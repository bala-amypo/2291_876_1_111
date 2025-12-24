package com.example.demo.service;

import com.example.demo.model.MatchAttemptRecord;
import java.util.*;

public interface MatchAttemptService {
    MatchAttemptRecord logMatchAttempt(MatchAttemptRecord a);
    MatchAttemptRecord updateAttemptStatus(Long id, String status);
    List<MatchAttemptRecord> getAttemptsByStudent(Long id);
    List<MatchAttemptRecord> getAllMatchAttempts();
}
