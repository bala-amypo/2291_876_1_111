package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class CompatibilityScoreRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long studentAId;
    private Long studentBId;
    private Double score;

    @Enumerated(EnumType.STRING)
    private CompatibilityLevel compatibilityLevel;

    private LocalDateTime computedAt;

    @Column(columnDefinition = "TEXT")
    private String detailsJson;

    public enum CompatibilityLevel {
        LOW, MEDIUM, HIGH, EXCELLENT
    }
}
