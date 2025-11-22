package com.example.umc9th.domain.mission.dto.res;

import lombok.Builder;

import java.time.LocalDateTime;

public class MissionResDTO {

    @Builder
    public record AddMemberMissionDTO(
        Long memberMissionId,
        LocalDateTime createdAt
    ) {}
}