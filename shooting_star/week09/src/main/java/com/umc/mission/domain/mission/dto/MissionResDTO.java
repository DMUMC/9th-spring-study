package com.umc.mission.domain.mission.dto;

import java.util.List;
import lombok.Builder;

public class MissionResDTO {
    @Builder
    public record MissionPreviewDTO(
            String storeName,
            String title,
            String description,
            Integer rewardPoint,
            Integer deadlineDays
    ) {
    }

    @Builder
    public record MissionPreviewListDTO(
            List<MissionPreviewDTO> missionPreviewDTOList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ) {
    }
}
