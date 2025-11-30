package com.umc.converter;

import com.umc.domain.Review;
import com.umc.dto.response.ReviewResponseDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public class ReviewConverter {

    public static ReviewResponseDTO.ReviewPreviewDTO toReviewPreviewDTO(Review review) {
        return ReviewResponseDTO.ReviewPreviewDTO.builder()
                .reviewId(review.getId())
                .storeName(review.getStore().getName())
                .nickname(review.getMember().getNickname())
                .rating(review.getRating())
                .content(review.getContent())
                .createdAt(review.getCreatedAt())
                .ownerReply(null)
                .ownerReplyDate(null)
                .build();
    }

    public static ReviewResponseDTO.ReviewPreviewListDTO toReviewPreviewListDTO(Page<Review> reviewPage) {
        List<ReviewResponseDTO.ReviewPreviewDTO> reviewList = reviewPage.stream()
                .map(ReviewConverter::toReviewPreviewDTO)
                .toList();

        return ReviewResponseDTO.ReviewPreviewListDTO.builder()
                .reviews(reviewList)
                .listSize(reviewList.size())
                .totalPage(reviewPage.getTotalPages())
                .totalElements(reviewPage.getTotalElements())
                .isFirst(reviewPage.isFirst())
                .isLast(reviewPage.isLast())
                .build();
    }
}
