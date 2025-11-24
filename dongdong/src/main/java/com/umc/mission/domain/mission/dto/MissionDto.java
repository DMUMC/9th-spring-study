package com.umc.mission.domain.mission.dto;

import com.umc.mission.domain.mission.entity.Mission;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;

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
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PageResponse {
        private List<Response> missions;
        private long totalElements;
        private int numberOfElements;
        private int totalPages;
        private int currentPage;
        private int pageSize;
        private boolean isFirst;
        private boolean isLast;

        public static PageResponse from(Page<Response> page) {
            return PageResponse.builder()
                    .missions(page.getContent())
                    .totalElements(page.getTotalElements())
                    .numberOfElements(page.getNumberOfElements())
                    .totalPages(page.getTotalPages())
                    .currentPage(page.getNumber() + 1) // 프론트엔드에 줄 때는 +1 해서 전달 (선택사항)
                    .pageSize(page.getSize())
                    .isFirst(page.isFirst())
                    .isLast(page.isLast())
                    .build();
        }
    }
}