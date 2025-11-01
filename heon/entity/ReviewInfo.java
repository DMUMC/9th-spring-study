package com.example.missionreview.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_review_info")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class ReviewInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tri_code")
    private Long triCode;

    @Column(name = "tri_tupcode", nullable = false)
    private Long tupCode; // 결재정보 PK

    @Column(name = "tri_context", columnDefinition = "TEXT")
    private String context;

    @Column(name = "tri_score")
    private int score;

    @Column(name = "tri_regidate")
    private LocalDateTime regidate = LocalDateTime.now();
}