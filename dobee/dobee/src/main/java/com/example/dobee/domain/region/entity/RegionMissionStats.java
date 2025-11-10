package com.example.dobee.domain.region.entity;

import com.example.dobee.domain.member.entity.Member;
import com.example.dobee.global.entity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(
        name = "region_mission_stats",
        uniqueConstraints = @UniqueConstraint(
                name = "unique_member_region",
                columnNames = {"member_id", "region_id"}
        )
)
public class RegionMissionStats extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id", nullable = false)
    private Region region;

    @NotNull
    @Column(name = "total_missions", nullable = false)
    @Builder.Default
    private Integer totalMissions = 10;

    @NotNull
    @Column(name = "completed_missions", nullable = false)
    @Builder.Default
    private Integer completedMissions = 0;

    @Column(name = "is_all_completed")
    @Builder.Default
    private Boolean isAllCompleted = false;

    @Column(name = "reward_given")
    @Builder.Default
    private Boolean rewardGiven = false;
}
