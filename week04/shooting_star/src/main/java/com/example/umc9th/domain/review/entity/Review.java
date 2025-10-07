package com.example.umc9th.domain.review.entity;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.store.entity.Store;
import com.example.umc9th.global.entity.BaseEntity;
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

    @Column(name = "review_text", nullable = false)
    private String reviewText;

    @Column(name = "review_score", nullable = false)
    private Float reviewScore;

    /*
    * byte[]로 선언하고 @Lob(Large Object) 어노테이션을 사용하여 이미지 데이터 자체를 저장해도 되지만
    * db 용량이 급격히 커지고, 백업 및 조회 성능이 크게 저하됨
    * 그래서 이미지는 aws나 google cloud storage 같은 클라우드 스토리지에 업로드하고
    * db에는 그 파일에 접근할 수 있는 경로만 저장하도록 함.
    */
    @Column(name = "review_picture_url")
    private String reviewPictureUrl;

    // 1:N 관계가 아니기 때문에 List 타입으로 선언하지 않음
    @OneToOne(mappedBy = "review", cascade = CascadeType.REMOVE)
    private ReviewReply reviewReply;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;
}
