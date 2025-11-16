package com.example.umc9th.domain.review.converter;

import com.example.umc9th.domain.member.converter.MemberConverter;
import com.example.umc9th.domain.review.dto.res.ReviewResDTO;
import com.example.umc9th.domain.review.entity.Review;

import java.util.List;
import java.util.stream.Collectors;

public class ReviewConverter {

    public static ReviewResDTO.ReviewDTO toReviewDTO(Review review) {
        return ReviewResDTO.ReviewDTO.builder()
                .reviewId(review.getId())
                .content(review.getContent())
                .star(review.getStar())
                .createdAt(review.getCreatedAt())
                .writer(MemberConverter.toWriterDTO(review.getMember()))
                .build();
    }

    public static List<ReviewResDTO.ReviewDTO> toReviewDTOList(List<Review> reviewList) {
        return reviewList.stream()
                .map(ReviewConverter::toReviewDTO)
                .collect(Collectors.toList());
    }
}