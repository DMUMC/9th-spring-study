package com.umc.mission.domain.review.service;

import com.umc.mission.domain.review.entity.Review;
import org.springframework.data.domain.Page;

public interface ReviewQueryService {
    Page<Review> findReviewByStoreId(Long storeId, Integer page);
    Page<Review> findReviewByMemberId(Long memberId, Integer page);
}