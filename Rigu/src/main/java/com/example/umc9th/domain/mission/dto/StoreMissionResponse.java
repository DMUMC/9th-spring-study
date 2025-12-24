package com.example.umc9th.domain.mission.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record StoreMissionResponse(
        Long missionId,
        String storeName,
        String content,
        Integer point,
        LocalDateTime deadline
) {
}
