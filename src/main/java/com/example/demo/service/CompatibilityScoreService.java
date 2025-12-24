package com.example.demo.service;

import com.example.demo.model.CompatibilityScoreRecord;
import java.util.*;

public interface CompatibilityScoreService {
    CompatibilityScoreRecord computeScore(Long a, Long b);
    List<CompatibilityScoreRecord> getScoresForStudent(Long id);
    CompatibilityScoreRecord getScoreById(Long id);
    List<CompatibilityScoreRecord> getAllScores();
}
