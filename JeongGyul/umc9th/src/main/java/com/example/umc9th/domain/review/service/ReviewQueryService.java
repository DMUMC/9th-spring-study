package com.example.umc9th.domain.review.service;

import com.example.umc9th.domain.review.converter.ReviewConverter;
import com.example.umc9th.domain.review.dto.res.ReviewResDTO;
import com.example.umc9th.domain.review.entity.QReview;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewQueryService {
    private final ReviewRepository reviewRepository;

    public List<ReviewResDTO.ReviewDTO> searchReview(Long memberId, Long storeId, Integer rating) {
        QReview review = QReview.review;
        BooleanBuilder builder = new BooleanBuilder();

        builder.and(review.member.id.eq(memberId));

        if (storeId != null) {
            builder.and(review.store.id.eq(storeId));
        }

        if (rating != null) {
            if (rating == 5) {
                builder.and(review.star.eq(5.0f));
            }
            else if (rating >= 1 && rating <= 4) {
                builder.and(review.star.goe(rating.floatValue()));
                builder.and(review.star.lt(rating.floatValue() + 1.0f));
            }
        }
        List<Review> reviewList = reviewRepository.searchReview(builder);
        return ReviewConverter.toReviewDTOList(reviewList);
    }
}