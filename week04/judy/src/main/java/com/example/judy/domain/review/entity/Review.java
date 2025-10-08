package com.example.judy.domain.review.entity;

import com.example.judy.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "review")
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "star", nullable = false)
    private Float star;

    // 연관 관계 설정
    @OneToMany
    @JoinColumn(name = "review_id", nullable = false)
    private List<ReviewPhoto> reviewPhotoList = new ArrayList<>();

    // 연관 관계 설정
    @OneToMany
    @JoinColumn(name = "review_id", nullable = false)
    private List<Reply> replyList = new ArrayList<>();

}
