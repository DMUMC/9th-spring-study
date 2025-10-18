package com.example.judy.domain.store.entity;

import com.example.judy.domain.mission.entity.Mission;
import com.example.judy.domain.review.entity.Review;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "store")
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 20, nullable = false)
    private String name;

    @Column(name = "address", length = 50, nullable = false)
    private String address;

    @Column(name = "owner_code", nullable = false)
    private Long ownerCode;

    // 연관 관계 설정
    @OneToMany
    @JoinColumn(name = "store_id", nullable = false)
    private List<Mission> missionList = new ArrayList<>();

    // 연관 관계 설정
    @OneToMany
    @JoinColumn(name = "store_id", nullable = false)
    private List<Review> reviewList = new ArrayList<>();

}