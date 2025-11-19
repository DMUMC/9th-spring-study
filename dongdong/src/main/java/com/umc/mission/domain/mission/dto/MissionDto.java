package com.umc.mission.domain.mission.dto;

import com.umc.mission.domain.mission.entity.Mission;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class MissionDto {

    @Getter
    public static class Request {
        @NotNull
        private Long storeId;
        @NotBlank
        private String title;
        private String description;
        @NotNull
        private Integer rewardPoint;
        @NotNull
        private Integer deadlineDays;
    }

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