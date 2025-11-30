package com.umc.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class MissionResponseDTO {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "미션 미리보기 DTO")
    public static class MissionPreviewDTO {
        @Schema(description = "미션 ID", example = "1")
        private Long missionId;

        @Schema(description = "가게 이름", example = "반이학생마라탕마라반")
        private String storeName;

        @Schema(description = "미션 제목", example = "3회 방문하기")
        private String title;

        @Schema(description = "미션 설명", example = "이번 달 안에 3회 방문하면 포인트 지급!")
        private String description;

        @Schema(description = "리워드 포인트", example = "1000")
        private Integer reward;

        @Schema(description = "미션 마감일", example = "2024-12-31")
        private LocalDate deadline;

        @Schema(description = "남은 일수", example = "30")
        private Integer daysRemaining;

        @Schema(description = "생성일시", example = "2024-01-15T14:30:00")
        private LocalDateTime createdAt;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "미션 목록 응답 DTO")
    public static class MissionPreviewListDTO {
        @Schema(description = "미션 목록")
        private List<MissionPreviewDTO> missions;

        @Schema(description = "현재 페이지 미션 개수", example = "10")
        private Integer listSize;

        @Schema(description = "전체 페이지 수", example = "5")
        private Integer totalPage;

        @Schema(description = "전체 미션 개수", example = "50")
        private Long totalElements;

        @Schema(description = "첫 페이지 여부", example = "true")
        private Boolean isFirst;

        @Schema(description = "마지막 페이지 여부", example = "false")
        private Boolean isLast;
    }
}
