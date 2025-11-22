package com.example.umc9th.domain.review.converter;

import com.example.umc9th.domain.member.converter.MemberConverter;
import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.review.dto.req.ReviewReqDTO;
import com.example.umc9th.domain.review.dto.res.ReviewResDTO;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.store.entity.Store;

import java.util.List;
import java.util.stream.Collectors;

public class ReviewConverter {

    // Review -> MyReviewDTO로 변환
    public static ReviewResDTO.MyReviewDTO toMyReviewDTO(Review review) {
        return ReviewResDTO.MyReviewDTO.builder()
                .reviewId(review.getId())
                .content(review.getContent())
                .star(review.getStar())
                .createdAt(review.getCreatedAt())
                .writer(MemberConverter.toWriterDTO(review.getMember()))
                .build();
    }

    // List<Review> -> List<MyReviewDTO>로 변환
    public static List<ReviewResDTO.MyReviewDTO> toMyReviewDTOList(List<Review> reviewList) {
        return reviewList.stream()
                .map(ReviewConverter::toMyReviewDTO)
                .collect(Collectors.toList());
    }

    // AddDTO -> Review로 변환 (리뷰 추가)
    public static Review toReview(ReviewReqDTO.AddDTO dto, Store store, Member member) {
        return Review.builder()
                .store(store)
                .member(member)
                .content(dto.content())
                .star(dto.star())
                .build();
    }

    // Review -> AddDTO로 변환 (리뷰 추가)
    public static ReviewResDTO.AddDTO toAddDTO(Review review) {
        return ReviewResDTO.AddDTO.builder()
                .reviewId(review.getId())
                .createdAt(review.getCreatedAt())
                .build();
    }
}