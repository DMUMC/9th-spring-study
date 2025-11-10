package com.example.dobee.domain.point.entity;

import com.example.dobee.domain.member.entity.Member;
import com.example.dobee.domain.mission.entity.MemberMission;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "point_history")
public class PointHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_mission_id")
    private MemberMission memberMission;

    @NotNull
    @Column(name = "point_change", nullable = false)
    private Integer pointChange;

    @NotNull
    @Column(name = "point_balance", nullable = false)
    private Integer pointBalance;

    @NotNull
    @Size(max = 20)
    @Column(nullable = false, length = 20)
    private String type;

    @Size(max = 200)
    @Column(length = 200)
    private String description;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false, columnDefinition = "DATETIME(6)")
    private LocalDateTime createdAt;
}
