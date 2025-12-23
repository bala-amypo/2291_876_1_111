package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "match_attempt_record")
@Data
public class MatchAttemptRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "initiator_student_id", nullable = false)
    private Long initiatorStudentId;

    @Column(name = "candidate_student_id", nullable = false)
    private Long candidateStudentId;

    @Column(name = "result_score_id")
    private Long resultScoreId;

    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDateTime attemptedAt = LocalDateTime.now();

    public enum Status {
        MATCHED, NOT_COMPATIBLE, PENDING_REVIEW
    }
}
