package com.umc.mission.domain.review.dto;

import com.umc.mission.domain.review.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ReviewDto {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {
        private Long memberId;
        private Long storeId;
        private BigDecimal rating;
        private String content;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private Long id;
        private Long memberId;
        private String memberName;
        private Long storeId;
        private String storeName;
        private BigDecimal rating;
        private String content;
        private String status;
        private LocalDateTime createdAt;

        public static Response from(Review review) {
            return Response.builder()
                    .id(review.getId())
                    .memberId(review.getMember().getId())
                    .memberName(review.getMember().getName())
                    .storeId(review.getStore().getId())
                    .storeName(review.getStore().getName())
                    .rating(review.getRating())
                    .content(review.getContent())
                    .status(review.getStatus().name())
                    .createdAt(review.getCreatedAt())
                    .build();
        }
    }
}