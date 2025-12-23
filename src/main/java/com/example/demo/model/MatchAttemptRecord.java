package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "match_attempts")
public class MatchAttemptRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long initiatorStudentId;
    private Long candidateStudentId;
    private Long resultScoreId;

    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDateTime attemptedAt = LocalDateTime.now();

    protected MatchAttemptRecord() {}

    public MatchAttemptRecord(Long initiatorStudentId, Long candidateStudentId,
                              Long resultScoreId, Status status) {
        this.initiatorStudentId = initiatorStudentId;
        this.candidateStudentId = candidateStudentId;
        this.resultScoreId = resultScoreId;
        this.status = status;
        this.attemptedAt = LocalDateTime.now();
    }

    public enum Status {
        MATCHED,
        NOT_COMPATIBLE,
        PENDING_REVIEW
    }
}
