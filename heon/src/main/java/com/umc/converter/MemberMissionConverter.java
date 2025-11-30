package com.umc.converter;

import com.umc.domain.MemberMission;
import com.umc.domain.Mission;
import com.umc.dto.response.MemberMissionResponseDTO;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class MemberMissionConverter {

    public static MemberMissionResponseDTO.MemberMissionPreviewDTO toMemberMissionPreviewDTO(MemberMission memberMission) {
        Mission mission = memberMission.getMission();
        int daysRemaining = (int) ChronoUnit.DAYS.between(LocalDate.now(), mission.getDeadline());

        return MemberMissionResponseDTO.MemberMissionPreviewDTO.builder()
                .memberMissionId(memberMission.getId())
                .missionId(mission.getId())
                .storeName(mission.getStore().getName())
                .title(mission.getTitle())
                .description(mission.getDescription())
                .reward(mission.getReward())
                .deadline(mission.getDeadline())
                .daysRemaining(Math.max(daysRemaining, 0))
                .status(memberMission.getStatus())
                .createdAt(memberMission.getCreatedAt())
                .build();
    }

    public static MemberMissionResponseDTO.MemberMissionPreviewListDTO toMemberMissionPreviewListDTO(Page<MemberMission> memberMissionPage) {
        List<MemberMissionResponseDTO.MemberMissionPreviewDTO> memberMissionList = memberMissionPage.stream()
                .map(MemberMissionConverter::toMemberMissionPreviewDTO)
                .toList();

        return MemberMissionResponseDTO.MemberMissionPreviewListDTO.builder()
                .memberMissions(memberMissionList)
                .listSize(memberMissionList.size())
                .totalPage(memberMissionPage.getTotalPages())
                .totalElements(memberMissionPage.getTotalElements())
                .isFirst(memberMissionPage.isFirst())
                .isLast(memberMissionPage.isLast())
                .build();
    }
}
