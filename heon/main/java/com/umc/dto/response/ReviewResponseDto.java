package com.umc.dto.response;

import com.umc.domain.Review;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@Schema(description = "리뷰 응답")
public class ReviewResponseDto {
    
    @Schema(description = "리뷰 ID", example = "1")
    private Long reviewId;
    
    @Schema(description = "작성자 이름", example = "홍길동")
    private String memberName;
    
    @Schema(description = "가게 이름", example = "맛있는 식당")
    private String storeName;
    
    @Schema(description = "평점", example = "4.5")
    private Float rating;
    
    @Schema(description = "리뷰 내용", example = "정말 맛있었어요!")
    private String content;
    
    @Schema(description = "작성일시", example = "2024-01-15T14:30:00")
    private LocalDateTime createdAt;
    
    public static ReviewResponseDto from(Review review) {
        return ReviewResponseDto.builder()
                .reviewId(review.getId())
                .memberName(review.getMember().getName())
                .storeName(review.getStore().getName())
                .rating(review.getRating())
                .content(review.getContent())
                .createdAt(review.getCreatedAt())
                .build();
    }
}
