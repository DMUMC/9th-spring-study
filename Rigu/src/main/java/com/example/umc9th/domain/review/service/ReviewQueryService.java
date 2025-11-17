package com.example.umc9th.domain.review.service;

import com.example.umc9th.domain.review.dto.ReviewResponse;
import com.example.umc9th.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewQueryService {

    private final ReviewRepository reviewRepository;

    public List<ReviewResponse> getMyReviews(
            Long memberId,
            String storeName,
            Integer minStar,
            Integer maxStar
    ) {
        return reviewRepository.findMyReviews(memberId, storeName, minStar, maxStar);
    }
}
