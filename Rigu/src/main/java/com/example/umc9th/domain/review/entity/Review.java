package com.example.umc9th.domain.review.entity;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.store.entity.Store;
import com.example.umc9th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "review")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private Member member;

    @Column(name = "rating", nullable = false)
    private Integer rating;

    @Lob
    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted = false;

    @Builder
    private Review(Store store, Member member, Integer rating, String content) {
        this.store = store;
        this.member = member;
        this.rating = rating;
        this.content = content;
        this.isDeleted = false;
    }
}
