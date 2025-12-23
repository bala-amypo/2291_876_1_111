package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "match_results")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MatchResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_a_id", nullable = false)
    private StudentProfile studentA;

    @ManyToOne
    @JoinColumn(name = "student_b_id", nullable = false)
    private StudentProfile studentB;

    @Column(nullable = false)
    private Double score;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(length = 1000)
    private String reasonSummary;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}