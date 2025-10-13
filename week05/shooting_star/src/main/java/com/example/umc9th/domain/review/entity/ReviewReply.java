package com.example.umc9th.domain.review.entity;

import com.example.umc9th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "review_reply")
public class ReviewReply extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    /*
    * @OneToOne, @ManyToOne 같은 어노테이션은 자바 객체에서의 관계를 jpa에게 알려주는 역할
    * @Table, @Column, @JoinColumn 같은 어노테이션은 자바의 객체 관계를 실제 db에 어떻게 표현할지
    * 알려주는 역할을 함.
    * 그러므로, @OneToOne 어노테이션을 통해 1:1 관계임을 명시해도, 실제 db에는 1:1 관계가 적용X
    * JoinColumn에 해당 unique 속성을 true로 해줌으로써 실제 1:1 관계 설정
    */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id", unique = true)
    private Review review;
}
