package com.example.umc9th.domain.mission.dto.req;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

public class MissionReqDTO {

    @Builder
    public record AddMemberMissionDTO(
            @NotNull
            Long memberId,
            @NotNull
            Long missionId
    ) {}
}