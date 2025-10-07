package com.example.umc9th.domain.member.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "terms")
public class Terms {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    // 내용이 길 수 있으므로, 칼럼 유형을 정의함
    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(name = "is_required", nullable = false)
    private Boolean isRequired;
}
