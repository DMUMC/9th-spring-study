package com.example.dobee.domain.review.repository;

import com.example.dobee.domain.review.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewQueryDsl {
    Page<Review> findMyReviews(Long userId, String storeName, Integer rating, Pageable pageable);
}