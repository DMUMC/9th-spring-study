package com.umc.mission.domain.review.repository;

import com.umc.mission.domain.review.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;

public interface ReviewRepositoryCustom {

    /**
     * 회원의 리뷰를 가게별, 별점별으로 필터링 조회
     */
    Page<Review> findMyReviewsByFilters(Long memberId, Long storeId,
                                        BigDecimal minRating, BigDecimal maxRating,
                                        Pageable pageable);
}