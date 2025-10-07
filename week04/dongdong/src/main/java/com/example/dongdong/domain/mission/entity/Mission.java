package com.example.dongdong.domain.mission.entity;

import com.example.dongdong.domain.missionMember.entity.MissionMember;
import com.example.dongdong.domain.store.entity.Store;
import com.example.dongdong.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Mission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String comment;

    private String address;

    private Boolean isComplete;

    private Integer givePoint;

    // 연관 관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @OneToMany(mappedBy = "mission", cascade = CascadeType.ALL)
    private List<MissionMember> missionMemberList = new ArrayList<>();
}