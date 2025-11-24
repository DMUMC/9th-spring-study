package com.umc.mission.domain.mission.converter;

import com.umc.mission.domain.mission.dto.MissionResDTO;
import com.umc.mission.domain.mission.entity.Mission;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Page;

public class MissionConverter {
    public static MissionResDTO.MissionPreviewDTO toMissionPreviewDTO(Mission mission) {
        return MissionResDTO.MissionPreviewDTO.builder()
                .storeName(mission.getStore().getName())
                .title(mission.getTitle())
                .description(mission.getDescription())
                .rewardPoint(mission.getRewardPoint())
                .deadlineDays(mission.getDeadlineDays())
                .build();
    }

    public static MissionResDTO.MissionPreviewListDTO toMissionPreviewListDTO(
            Page<Mission> missionPage
    ) {
        List<MissionResDTO.MissionPreviewDTO> missionPreviewDTOList = missionPage.stream()
                .map(MissionConverter::toMissionPreviewDTO).toList();

        return MissionResDTO.MissionPreviewListDTO.builder()
                .missionPreviewDTOList(missionPreviewDTOList)
                .listSize(missionPreviewDTOList.size())
                .totalPage(missionPage.getTotalPages())
                .totalElements(missionPage.getTotalElements())
                .isFirst(missionPage.isFirst())
                .isLast(missionPage.isLast())
                .build();
    }
}
