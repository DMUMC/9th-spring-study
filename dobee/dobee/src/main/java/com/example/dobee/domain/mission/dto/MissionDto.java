package com.example.dobee.domain.mission.dto;

import com.example.dobee.domain.mission.entity.Mission;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class MissionDto {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private Long id;
        private Long storeId;
        private String storeName;
        private String title;
        private String description;
        private Integer rewardPoint;
        private Integer deadlineDays;
        private String status;
        private LocalDateTime createdAt;

        public static Response from(Mission mission) {
            return Response.builder()
                    .id(mission.getId())
                    .storeId(mission.getStore().getId())
                    .storeName(mission.getStore().getName())
                    .title(mission.getTitle())
                    .description(mission.getDescription())
                    .rewardPoint(mission.getRewardPoint())
                    .deadlineDays(mission.getDeadlineDays())
                    .status(mission.getStatus().name())
                    .createdAt(mission.getCreatedAt())
                    .build();
        }
    }
}
