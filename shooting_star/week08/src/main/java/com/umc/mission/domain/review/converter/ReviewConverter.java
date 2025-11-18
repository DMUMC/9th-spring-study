package com.umc.mission.domain.review.converter;

import com.umc.mission.domain.member.entity.Member;
import com.umc.mission.domain.review.dto.StoreReviewReqDTO;
import com.umc.mission.domain.review.dto.StoreReviewResDTO;
import com.umc.mission.domain.review.entity.Review;
import com.umc.mission.domain.store.entity.Store;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ReviewConverter {
    // dto -> entity (리뷰 생성할 때)
    public static Review toEntity(
            StoreReviewReqDTO.CreateReviewDTO request,
            Member member,
            Store store
    ){
        return Review.builder()
                .member(member)
                .store(store)
                .content(request.getContent())
                // 요청을 받을 때는 Double 타입으로 받았으므로 BigDecimal로 변환
                .rating(BigDecimal.valueOf(request.getRating()))
                .build();
    }


    // entity -> dto (응답할 때)
    public static StoreReviewResDTO.CreateReviewResultDTO toCreateResultDTO(
            Review review
    ){
        return StoreReviewResDTO.CreateReviewResultDTO.builder()
                .reviewId(review.getId())
                .createAt(review.getCreatedAt())
                .build();
    }
}
