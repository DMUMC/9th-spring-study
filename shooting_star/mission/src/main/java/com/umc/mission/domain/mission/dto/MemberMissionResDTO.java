package com.umc.mission.domain.mission.dto;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;

public class MemberMissionResDTO {
    @Builder
    public record ChallengingMissionPreviewDTO(
            String storeName,
            String missionTitle,
            Integer reawardPoint,
            LocalDateTime deadlineAt
    ) {
    }

    @Builder
    public record ChallengingMissionPreviewListDTO(
            List<ChallengingMissionPreviewDTO> challengingMissionPreviewDTO,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ) {
    }

    @Builder
    public record CompleteMissionPreviewDTO(
            String storeName,
            String missionTitle,
            Integer reawardPoint,
            String status
    ) {
    }

    @Builder
    public record CompleteMissionPreviewListDTO(
            List<MemberMissionResDTO.CompleteMissionPreviewDTO> completeMissionPreviewDTO,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ){
    }
}
