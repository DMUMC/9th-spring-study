package com.example.dobee.domain.review.service.query;

import com.example.dobee.domain.review.dto.ReviewDto;

public interface ReviewQueryService {
    ReviewDto.ReviewPreViewListResDto getMyReviews(Long memberId, Integer page, String storeName, Integer rating);
}
