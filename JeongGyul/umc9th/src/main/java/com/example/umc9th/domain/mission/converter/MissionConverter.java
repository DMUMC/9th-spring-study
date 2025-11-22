package com.example.umc9th.domain.mission.converter;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.mission.dto.res.MissionResDTO;
import com.example.umc9th.domain.mission.entity.MemberMission;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.enums.MissionStatus;

public class MissionConverter {

    // MemberMissionDTO -> MemberMission로 변환 (미션 도전)
    public static MemberMission toMemberMission(
            Member member,
            Mission mission
    ) {
        return MemberMission.builder()
                .member(member)
                .mission(mission)
                .status(MissionStatus.IN_PROGRESS)
                .build();
    }

    // MemberMission -> AddDTO로 변환 (미션 도전)
    public static MissionResDTO.AddMemberMissionDTO toMemberMissionDTO(MemberMission memberMission) {
        return MissionResDTO.AddMemberMissionDTO.builder()
                .memberMissionId(memberMission.getId())
                .createdAt(memberMission.getCreatedAt())
                .build();
    }
}