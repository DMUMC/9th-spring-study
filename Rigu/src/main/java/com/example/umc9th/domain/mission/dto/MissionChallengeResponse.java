package com.example.umc9th.domain.mission.dto;

import java.time.LocalDateTime;

public record MissionChallengeResponse(
        Long userMissionId,
        String status,
        LocalDateTime createdAt
) {
}