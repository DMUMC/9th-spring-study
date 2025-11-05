package com.umc.mission.domain.review.repository;

import com.querydsl.core.types.Predicate;
import com.umc.mission.domain.review.entity.Review;

import java.util.List;

public interface ReviewQueryDsl {
    List<Review> searchMyReview(Predicate predicate);
}
