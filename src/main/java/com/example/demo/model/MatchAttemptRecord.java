package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class MatchAttemptRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long initiatorStudentId;
    private Long candidateStudentId;
    private Long resultScoreId;

    @Enumerated(EnumType.STRING)
    private Status status;

    public enum Status {
        PENDING_REVIEW,
        MATCHED
    }
}
