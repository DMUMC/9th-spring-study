package com.example.umc9th.domain.review.dto;

public record ReviewCreateRequest(
        Long memberId,
        Integer rating,
        String content
) {}
