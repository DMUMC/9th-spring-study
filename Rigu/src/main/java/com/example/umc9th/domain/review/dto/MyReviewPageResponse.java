package com.example.umc9th.domain.review.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record MyReviewPageResponse(
        List<ReviewResponse> reviews,
        Integer page,
        Integer size,
        Long totalElements,
        Integer totalPages,
        Boolean first,
        Boolean last
) {
}
