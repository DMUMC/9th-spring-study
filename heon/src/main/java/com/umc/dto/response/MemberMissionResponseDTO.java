package com.umc.dto.response;

import com.umc.domain.enums.MissionStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class MemberMissionResponseDTO {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "회원 미션 미리보기 DTO")
    public static class MemberMissionPreviewDTO {
        @Schema(description = "회원 미션 ID", example = "1")
        private Long memberMissionId;

        @Schema(description = "미션 ID", example = "1")
        private Long missionId;

        @Schema(description = "가게 이름", example = "반이학생마라탕마라반")
        private String storeName;

        @Schema(description = "미션 제목", example = "리뷰 작성하기")
        private String title;

        @Schema(description = "미션 설명", example = "가게에 리뷰를 작성하면 포인트를 드립니다.")
        private String description;

        @Schema(description = "보상 포인트", example = "500")
        private Integer reward;

        @Schema(description = "마감일", example = "2024-12-31")
        private LocalDate deadline;

        @Schema(description = "남은 일수", example = "30")
        private Integer daysRemaining;

        @Schema(description = "미션 상태", example = "CHALLENGING")
        private MissionStatus status;

        @Schema(description = "도전 시작일", example = "2024-05-14T00:00:00")
        private LocalDateTime createdAt;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "회원 미션 목록 응답 DTO")
    public static class MemberMissionPreviewListDTO {
        @Schema(description = "회원 미션 목록")
        private List<MemberMissionPreviewDTO> memberMissions;

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
