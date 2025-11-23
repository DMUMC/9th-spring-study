package com.example.umc9th.domain.review.dto;

import java.time.LocalDateTime;

public record ReviewCreateResponse(Long reviewId, LocalDateTime createdAt) {
}