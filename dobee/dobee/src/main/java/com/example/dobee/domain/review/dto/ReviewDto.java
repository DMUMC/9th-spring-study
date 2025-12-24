package com.example.dobee.domain.review.dto;

import com.example.dobee.domain.review.entity.Review;
import com.example.dobee.global.annotation.ExistMember;
import com.example.dobee.global.annotation.ExistStore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

public class ReviewDto {

    @Getter
    @Setter
    public static class Request {
        private Long userId;
        private String storeName;
        private Integer rating;
    }

    @Getter
    @Builder
    public static class ReviewPreViewResDto {

        private Long reviewId;
        private String content;
        private Float star;
        private String storeName;
        private String memberName;

        public static ReviewPreViewResDto from(Review review) {
            return ReviewPreViewResDto.builder()
                    .reviewId(review.getId())
                    .content(review.getContent())
                    .star(review.getStar())
                    .storeName(review.getStore().getName())
                    .memberName(review.getMember().getNickname())
                    .build();
        }
    }

    public record AddReviewReqDto(
            @NotNull @ExistMember
            Long memberId,
            @NotNull @ExistStore
            Long storeId,
            @NotBlank
            String content,
            @NotNull
            Float star
    ) {}

    public record AddReviewResDto(
            Long reviewId,
            String storeName,
            String memberNickname,
            LocalDateTime createdAt
    ) {}

    @Builder
    @Getter
    public static class ReviewPreViewListResDto {
        private List<ReviewPreViewResDto> reviewList;
        private Integer listSize;
        private Integer totalPage;
        private Long totalElements;
        private Boolean isFirst;
        private Boolean isLast;
    }
}
