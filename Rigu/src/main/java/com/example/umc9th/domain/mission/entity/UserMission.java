package com.example.umc9th.domain.mission.entity;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_mission")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserMission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 도전 대상 미션
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "mission_id", nullable = false)
    private Mission mission;

    // 도전자
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private Member member;

    @Column(name = "status", nullable = false, length = 20)
    private String status;

    @Builder
    private UserMission(Member member, Mission mission, String status) {
        this.member = member;
        this.mission = mission;
        this.status = status;
    }
}
