package com.umc.mission.domain.mission.converter;

import com.umc.mission.domain.mission.dto.MemberMissionResDTO;
import com.umc.mission.domain.mission.dto.MemberMissionResDTO.ChallengingMissionPreviewDTO;
import com.umc.mission.domain.mission.entity.MemberMission;
import java.util.List;
import org.springframework.data.domain.Page;

public class MemberMissionConverter {
    public static MemberMissionResDTO.ChallengingMissionPreviewDTO toChallengingMissionPreviewDTO(
            MemberMission memberMission
    ){
        return MemberMissionResDTO.ChallengingMissionPreviewDTO.builder()
                .storeName(memberMission.getMission().getStore().getName())
                .missionTitle(memberMission.getMission().getTitle())
                .reawardPoint(memberMission.getMission().getRewardPoint())
                .deadlineAt(memberMission.getDeadlineAt())
                .build();
    }

    public static MemberMissionResDTO.ChallengingMissionPreviewListDTO toChallengingMissionPreviewListDTO(
            Page<MemberMission> memberMissions
    ){
        List<ChallengingMissionPreviewDTO> memberMissionPreviewList = memberMissions.stream()
                .map(MemberMissionConverter::toChallengingMissionPreviewDTO).toList();

        return MemberMissionResDTO.ChallengingMissionPreviewListDTO.builder()
                .challengingMissionPreviewDTO(memberMissionPreviewList)
                .listSize(memberMissionPreviewList.size())
                .totalPage(memberMissions.getTotalPages())
                .totalElements(memberMissions.getTotalElements())
                .isFirst(memberMissions.isFirst())
                .isLast(memberMissions.isLast())
                .build();
    }
}
