package com.umc.dto.response;

import com.umc.domain.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SimpleReviewResponseDto {
    
    private Long id;
    private Long memberId;
    private String memberNickname;
    private Long storeId;
    private String storeName;
    private Float rating;
    private String content;
    private LocalDateTime createdAt;
    
    public static SimpleReviewResponseDto from(Review review) {
        return SimpleReviewResponseDto.builder()
                .id(review.getId())
                .memberId(review.getMember().getId())
                .memberNickname(review.getMember().getNickname())
                .storeId(review.getStore().getId())
                .storeName(review.getStore().getName())
                .rating(review.getRating())
                .content(review.getContent())
                .createdAt(review.getCreatedAt())
                .build();
    }
}
