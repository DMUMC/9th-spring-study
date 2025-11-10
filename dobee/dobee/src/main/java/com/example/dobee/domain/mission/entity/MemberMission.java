package com.example.dobee.domain.mission.entity;

import com.example.dobee.domain.member.entity.Member;
import com.example.dobee.domain.mission.enums.MemberMissionStatus;
import com.example.dobee.domain.point.entity.PointHistory;
import com.example.dobee.domain.review.entity.Review;
import com.example.dobee.global.entity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(
        name = "member_mission",
        uniqueConstraints = @UniqueConstraint(
                name = "unique_member_mission",
                columnNames = {"member_id", "mission_id"}
        )
)
public class MemberMission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id", nullable = false)
    private Mission mission;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    @Builder.Default
    private MemberMissionStatus status = MemberMissionStatus.ONGOING;

    @NotNull
    @Column(name = "started_at", nullable = false, columnDefinition = "DATETIME(6)")
    @Builder.Default
    private LocalDateTime startedAt = LocalDateTime.now();

    @Column(name = "completed_at", columnDefinition = "DATETIME(6)")
    private LocalDateTime completedAt;

    @NotNull
    @Column(name = "deadline_at", nullable = false, columnDefinition = "DATETIME(6)")
    private LocalDateTime deadlineAt;

    @OneToMany(mappedBy = "memberMission", cascade = CascadeType.ALL)
    @Builder.Default
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "memberMission", cascade = CascadeType.ALL)
    @Builder.Default
    private List<PointHistory> pointHistories = new ArrayList<>();
}
