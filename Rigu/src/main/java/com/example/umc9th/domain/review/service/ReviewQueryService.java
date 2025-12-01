package com.example.umc9th.domain.review.service;

import com.example.umc9th.domain.review.dto.MyReviewPageResponse;
import com.example.umc9th.domain.review.dto.ReviewResponse;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewQueryService {

    private final ReviewRepository reviewRepository;

    public MyReviewPageResponse getMyReviews(
            Long memberId,
            String storeName,
            Integer minStar,
            Integer maxStar,
            Integer page
    ) {
        if (page == null || page < 1) {
            throw new IllegalArgumentException("page는 1 이상의 정수여야 합니다.");
        }

        PageRequest pageRequest = PageRequest.of(page - 1, 10);

        Page<ReviewResponse> result = reviewRepository.findMyReviews(
                memberId,
                storeName,
                minStar,
                maxStar,
                pageRequest
        );

        return MyReviewPageResponse.builder()
                .reviews(result.getContent().stream().toList())
                .page(result.getNumber() + 1)
                .size(result.getSize())
                .totalElements(result.getTotalElements())
                .totalPages(result.getTotalPages())
                .first(result.isFirst())
                .last(result.isLast())
                .build();
    }
}
