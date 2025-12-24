package com.example.dobee.domain.review.converter;

import com.example.dobee.domain.member.entity.Member;
import com.example.dobee.domain.review.dto.ReviewDto;
import com.example.dobee.domain.review.entity.Review;
import com.example.dobee.domain.store.entity.Store;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class ReviewConverter {

    public static Review toReview(ReviewDto.AddReviewReqDto request, Member member, Store store) {
        return Review.builder()
                .content(request.content())
                .star(request.star())
                .member(member)
                .store(store)
                .build();
    }

    public static ReviewDto.AddReviewResDto toAddReviewResDto(Review review) {
        return new ReviewDto.AddReviewResDto(
                review.getId(),
                review.getStore().getName(),
                review.getMember().getNickname(),
                review.getCreatedAt()
        );
    }

    public static ReviewDto.ReviewPreViewResDto toReviewPreViewResDto(Review review) {
        return ReviewDto.ReviewPreViewResDto.builder()
                .reviewId(review.getId())
                .content(review.getContent())
                .star(review.getStar())
                .storeName(review.getStore().getName())
                .memberName(review.getMember().getNickname())
                .build();
    }

    public static ReviewDto.ReviewPreViewListResDto toReviewPreViewListResDto(Page<Review> reviewPage) {
        List<ReviewDto.ReviewPreViewResDto> reviewList = reviewPage.getContent().stream()
                .map(ReviewConverter::toReviewPreViewResDto)
                .collect(Collectors.toList());

        return ReviewDto.ReviewPreViewListResDto.builder()
                .reviewList(reviewList)
                .listSize(reviewList.size())
                .totalPage(reviewPage.getTotalPages())
                .totalElements(reviewPage.getTotalElements())
                .isFirst(reviewPage.isFirst())
                .isLast(reviewPage.isLast())
                .build();
    }
}