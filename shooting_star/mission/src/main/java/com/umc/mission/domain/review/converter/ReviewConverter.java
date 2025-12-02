package com.umc.mission.domain.review.converter;

import com.umc.mission.domain.member.entity.Member;
import com.umc.mission.domain.review.dto.ReviewResDTO;
import com.umc.mission.domain.review.dto.StoreReviewReqDTO;
import com.umc.mission.domain.review.dto.StoreReviewResDTO;
import com.umc.mission.domain.review.entity.Review;
import com.umc.mission.domain.store.entity.Store;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.domain.Page;

public class ReviewConverter {
    // dto -> entity (리뷰 생성할 때)
    public static Review toEntity(
            StoreReviewReqDTO.CreateReviewDTO request,
            Member member,
            Store store
    ) {
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
    ) {
        return StoreReviewResDTO.CreateReviewResultDTO.builder()
                .reviewId(review.getId())
                .createAt(review.getCreatedAt())
                .build();
    }


    public static ReviewResDTO.ReviewPreviewDTO toReviewPreviewDTO(Review review) {
        return ReviewResDTO.ReviewPreviewDTO.builder()
                .ownerNickname(review.getMember().getNickname())
                .rating(review.getRating())
                .body(review.getContent())
                .createdAt(review.getCreatedAt())
                .build();
    }

    // Page 객체의 reviewPage -> DTO
    public static ReviewResDTO.ReviewPreviewListDTO toReviewPreviewListDTO(Page<Review> reviewPage) {
        List<ReviewResDTO.ReviewPreviewDTO> reviewPreviewDTOList = reviewPage.getContent()
                .stream().map(ReviewConverter::toReviewPreviewDTO).toList();

        return ReviewResDTO.ReviewPreviewListDTO.builder()
                .reviewList(reviewPreviewDTOList)
                /*
                    listSize에 값을 지정할 때 reviewPage.getSize()는 요청한 페이지 크기를 의미하지만
                    마지막 페이지에서는 실제 데이터가 5개보다 적을 수 있음
                    그러므로 실제 반환된 리스트의 size()를 사용함
                 */
                .listSize(reviewPreviewDTOList.size())
                .totalElements(reviewPage.getTotalElements())
                .totalPage(reviewPage.getTotalPages())
                .isFirst(reviewPage.isFirst())
                .isLast(reviewPage.isLast())
                .build();
    }
}
