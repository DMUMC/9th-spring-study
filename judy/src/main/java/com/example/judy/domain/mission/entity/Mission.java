package com.example.judy.domain.mission.entity;

import com.example.judy.domain.mission.entity.mapping.MemberMission;
import com.example.judy.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import java.time.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "mission")
public class Mission extends BaseEntity
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content", length = 50, nullable = false)
    private String content;

    @Column(name = "point", nullable = false)
    @Builder.Default
    private Integer point = 0;

    @Column(name = "deadline", nullable = false)
    private LocalDate deadline;

    // 연관 관계 설정
    @OneToMany
    private List<MemberMission> memberMissionList = new ArrayList<>();

}