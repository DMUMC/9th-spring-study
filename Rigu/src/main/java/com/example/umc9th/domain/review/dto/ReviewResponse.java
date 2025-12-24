package com.example.umc9th.domain.review.dto;

import java.time.LocalDateTime;

public record ReviewResponse(
        Long id,
        String storeName,
        Integer rating,
        String content,
        LocalDateTime createdAt
) {
}