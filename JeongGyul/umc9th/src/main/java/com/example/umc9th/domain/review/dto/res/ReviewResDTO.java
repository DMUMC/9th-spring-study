package com.example.umc9th.domain.review.dto.res;

import com.example.umc9th.domain.member.dto.res.MemberResDTO;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

public class ReviewResDTO {

    @Builder
    @Getter
    public static class MyReviewDTO {
        private Long reviewId;
        private String content;
        private Float star;
        private LocalDateTime createdAt;
        private MemberResDTO.WriterDTO writer;
    }

    @Builder
    public record AddDTO(
            Long reviewId,
            LocalDateTime createdAt
    ) {}
}