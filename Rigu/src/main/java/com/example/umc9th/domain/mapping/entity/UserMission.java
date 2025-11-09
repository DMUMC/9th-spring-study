package com.example.umc9th.domain.mapping.entity;

import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.global.entity.BaseEntity;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_mission")
public class UserMission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="is_complete", nullable=false)
    private boolean isComplete = false;

    @Column(name="joined_at")
    private LocalDateTime joinedAt;

    @Column(name="completed_at")
    private LocalDateTime completedAt;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id", nullable=false)
    private Member member;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="mission_id", nullable=false)
    private Mission mission;
}
