package com.umc.domain;

import com.umc.domain.common.BaseEntity;
import com.umc.domain.enums.MissionStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MemberMission extends BaseEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id", nullable = false)
    private Mission mission;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private MissionStatus status = MissionStatus.CHALLENGING;
    
    public void setMember(Member member) {
        if (this.member != null) {
            this.member.getMemberMissions().remove(this);
        }
        this.member = member;
        member.getMemberMissions().add(this);
    }
    
    public void setMission(Mission mission) {
        if (this.mission != null) {
            this.mission.getMemberMissions().remove(this);
        }
        this.mission = mission;
        mission.getMemberMissions().add(this);
    }
    
    public void complete() {
        this.status = MissionStatus.COMPLETE;
    }
}
