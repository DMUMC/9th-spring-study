package com.example.dobee.domain.review.repository;

import com.example.dobee.domain.review.dto.ReviewDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewRepositoryCustom {
    Page<ReviewDto.Response> findByReviews(Long memberId, String storeName, Integer rating, Pageable pageable);
}
