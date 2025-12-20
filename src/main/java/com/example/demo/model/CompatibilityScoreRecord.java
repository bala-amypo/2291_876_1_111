package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "compatibility_score_records")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompatibilityScoreRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private Long studentAId;
    
    @Column(nullable = false)
    private Long studentBId;
    
    private Double score;
    
    @Enumerated(EnumType.STRING)
    private CompatibilityLevel compatibilityLevel;
    
    @Column(name = "computed_at")
    private LocalDateTime computedAt;
    
    @Column(length = 1000)
    private String detailsJson;
    
    @PrePersist
    protected void onCreate() {
        computedAt = LocalDateTime.now();
    }
}

public static enum CompatibilityLevel {
    LOW, MEDIUM, HIGH, EXCELLENT
}
