package com.example.dobee.domain.mission.dto;

import com.example.dobee.domain.mission.entity.Mission;
import com.example.dobee.domain.store.entity.Store;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

public class MissionDto {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor // 테스트 등에서 직접 생성할 경우를 위해 추가 권장
    @Builder            // 테스트 편의성을 위해 추가 권장
    public static class AddMissionRequest {

        @NotBlank(message = "미션 제목은 필수입니다.")
        @Size(max = 100, message = "미션 제목은 100자를 초과할 수 없습니다.")
        private String title;

        @NotBlank(message = "미션 내용은 필수입니다.")
        private String description;

        @NotNull(message = "보상 포인트는 필수입니다.")
        @Positive(message = "보상 포인트는 양수여야 합니다.")
        private Integer rewardPoint;

        @NotNull(message = "미션 마감일은 필수입니다.")
        @Min(value = 1, message = "미션 마감 기한은 최소 1일 이상이어야 합니다.")
        private Integer deadlineDays;

        public Mission toEntity(Store store) {
            return Mission.builder()
                    .store(store)
                    .title(this.title)
                    .description(this.description)
                    .rewardPoint(this.rewardPoint)
                    .deadlineDays(this.deadlineDays)
                    .build();
        }
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
