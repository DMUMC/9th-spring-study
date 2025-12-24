package com.example.umc9th.domain.mission.converter;

import com.example.umc9th.domain.mission.dto.StoreMissionPageResponse;
import com.example.umc9th.domain.mission.dto.StoreMissionResponse;
import com.example.umc9th.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;

public class MissionConverter {

    public static StoreMissionPageResponse toStoreMissionPageResponse(Page<Mission> page) {
        return StoreMissionPageResponse.builder()
                .missions(
                        page.getContent().stream()
                                .map(MissionConverter::toStoreMissionResponse)
                                .toList()
                )
                .page(page.getNumber() + 1)
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .first(page.isFirst())
                .last(page.isLast())
                .build();
    }

    // Mission -> StoreMissionResponse
    public static StoreMissionResponse toStoreMissionResponse(Mission mission) {
        return StoreMissionResponse.builder()
                .missionId(mission.getId())
                .storeName(mission.getStore().getName())
                .content(mission.getContent())
                .point(mission.getPoint())
                .deadline(mission.getDeadline())
                .build();
    }
}
