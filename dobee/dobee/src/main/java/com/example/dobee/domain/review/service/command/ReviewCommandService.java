package com.example.dobee.domain.review.service.command;

import com.example.dobee.domain.review.dto.ReviewDto;
import com.example.dobee.domain.review.entity.Review;

public interface ReviewCommandService {
    Review addReview(ReviewDto.AddReviewReqDto request);
}
