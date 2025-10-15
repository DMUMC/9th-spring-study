package com.example.dobee.domain.review.dto.request;

import lombok.Getter;

@Getter
public class ReviewRequestDto {
    private Long userId;
    private Long storeId;
    private Integer rating;
    private String content;
}
