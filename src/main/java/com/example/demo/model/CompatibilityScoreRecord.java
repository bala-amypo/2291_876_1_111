package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;


@Entity
@Table(name = "compatibility_score_records")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompatibilityScoreRecord {

    public enum CompatibilityLevel {
    LOW,
    MEDIUM,
    HIGH,
    EXCELLENT
}
                    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long studentAId;
    private Long studentBId;
    private Double score;

    @Enumerated(EnumType.STRING)
    private CompatibilityLevel compatibilityLevel;

    private String detailsJson;
    private LocalDateTime computedAt;

    @PrePersist
    void onCreate() {
        computedAt = LocalDateTime.now();
    }
}
