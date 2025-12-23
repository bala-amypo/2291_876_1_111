package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "match_attempt_records")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MatchAttemptRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long initiatorStudentId;

    @Column(nullable = false)
    private Long candidateStudentId;

    private Long resultScoreId;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false, updatable = false)
    private LocalDateTime attemptedAt = LocalDateTime.now();

    @PrePersist
    protected void onCreate() {
        attemptedAt = LocalDateTime.now();
    }
}