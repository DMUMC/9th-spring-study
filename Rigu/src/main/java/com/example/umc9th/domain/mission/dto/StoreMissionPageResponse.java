package com.example.umc9th.domain.mission.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record StoreMissionPageResponse(
        List<StoreMissionResponse> missions,
        Integer page,
        Integer size,
        Long totalElements,
        Integer totalPages,
        Boolean first,
        Boolean last
) {
}
