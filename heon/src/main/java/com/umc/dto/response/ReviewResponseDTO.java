package com.umc.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

public class ReviewResponseDTO {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "리뷰 미리보기 DTO")
    public static class ReviewPreviewDTO {
        @Schema(description = "리뷰 ID", example = "1")
        private Long reviewId;

        @Schema(description = "가게 이름", example = "반이학생마라탕마라반")
        private String storeName;

        @Schema(description = "작성자 닉네임", example = "닉네임1234")
        private String nickname;

        @Schema(description = "평점", example = "4.5")
        private Float rating;

        @Schema(description = "리뷰 내용", example = "음 너무 맛있어요 포인트도 얻고 맛있는 맛집도 알게 된 것 같아 너무너무 행복한 식사였습니다.")
        private String content;

        @Schema(description = "작성일시", example = "2024-05-14T00:00:00")
        private LocalDateTime createdAt;

        @Schema(description = "사장님 답글", example = "감사합니다.")
        private String ownerReply;

        @Schema(description = "사장님 답글 작성일", example = "2024-05-15")
        private String ownerReplyDate;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "리뷰 목록 응답 DTO")
    public static class ReviewPreviewListDTO {
        @Schema(description = "리뷰 목록")
        private List<ReviewPreviewDTO> reviews;

        @Schema(description = "현재 페이지 리뷰 개수", example = "10")
        private Integer listSize;

        @Schema(description = "전체 페이지 수", example = "5")
        private Integer totalPage;

        @Schema(description = "전체 리뷰 개수", example = "50")
        private Long totalElements;

        @Schema(description = "첫 페이지 여부", example = "true")
        private Boolean isFirst;

        @Schema(description = "마지막 페이지 여부", example = "false")
        private Boolean isLast;
    }
}
