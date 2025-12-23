package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(
    name = "compatibility_scores",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"studentAId", "studentBId"})
    }
)
public class CompatibilityScoreRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long studentAId;
    private Long studentBId;
    private Double score;

    @Enumerated(EnumType.STRING)
    private CompatibilityLevel compatibilityLevel;

    private LocalDateTime computedAt = LocalDateTime.now();

    @Column(columnDefinition = "TEXT")
    private String detailsJson;

    protected CompatibilityScoreRecord() {}

    public CompatibilityScoreRecord(Long studentAId, Long studentBId,
                                    Double score, CompatibilityLevel compatibilityLevel,
                                    String detailsJson) {
        this.studentAId = studentAId;
        this.studentBId = studentBId;
        this.score = score;
        this.compatibilityLevel = compatibilityLevel;
        this.detailsJson = detailsJson;
        this.computedAt = LocalDateTime.now();
    }

    public enum CompatibilityLevel {
        LOW, MEDIUM, HIGH, EXCELLENT
    }
}
