package com.umc.mission.domain.review.service;

import com.umc.mission.domain.review.dto.StoreReviewReqDTO;
import com.umc.mission.domain.review.entity.Review;

public interface ReviewCommandService {
    Review createReview(Long memberId, Long storeId, StoreReviewReqDTO.CreateReviewDTO request);
}
