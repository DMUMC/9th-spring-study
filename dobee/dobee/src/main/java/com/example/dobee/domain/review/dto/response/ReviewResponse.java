package com.example.dobee.domain.review.dto.response;

import com.example.dobee.domain.review.entity.Review;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReviewResponse {

    private Long reviewId;
    private String content;
    private Float star;
    private String storeName;
    private String memberName;

    public static ReviewResponse from(Review review) {
        return ReviewResponse.builder()
                .reviewId(review.getId())
                .content(review.getContent())
                .star(review.getStar())
                .storeName(review.getStore().getName())
                .memberName(review.getMember().getNickname())
                .build();
    }
}
