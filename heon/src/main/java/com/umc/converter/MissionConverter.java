package com.umc.converter;

import com.umc.domain.Mission;
import com.umc.dto.response.MissionResponseDTO;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class MissionConverter {

    public static MissionResponseDTO.MissionPreviewDTO toMissionPreviewDTO(Mission mission) {
        int daysRemaining = (int) ChronoUnit.DAYS.between(LocalDate.now(), mission.getDeadline());

        return MissionResponseDTO.MissionPreviewDTO.builder()
                .missionId(mission.getId())
                .storeName(mission.getStore().getName())
                .title(mission.getTitle())
                .description(mission.getDescription())
                .reward(mission.getReward())
                .deadline(mission.getDeadline())
                .daysRemaining(Math.max(daysRemaining, 0))
                .createdAt(mission.getCreatedAt())
                .build();
    }

    public static MissionResponseDTO.MissionPreviewListDTO toMissionPreviewListDTO(Page<Mission> missionPage) {
        List<MissionResponseDTO.MissionPreviewDTO> missionList = missionPage.stream()
                .map(MissionConverter::toMissionPreviewDTO)
                .toList();

        return MissionResponseDTO.MissionPreviewListDTO.builder()
                .missions(missionList)
                .listSize(missionList.size())
                .totalPage(missionPage.getTotalPages())
                .totalElements(missionPage.getTotalElements())
                .isFirst(missionPage.isFirst())
                .isLast(missionPage.isLast())
                .build();
    }
}
