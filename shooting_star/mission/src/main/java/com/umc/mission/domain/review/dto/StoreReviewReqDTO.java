package com.umc.mission.domain.review.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

public class StoreReviewReqDTO {
    @Getter
    public static class CreateReviewDTO{
        @NotBlank(message = "리뷰 내용은 필수입니다.")
        private String content;

        @NotNull(message = "별점은 필수입니다.")
        private Double rating;
    }
}
