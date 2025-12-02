package com.umc.mission.domain.review.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;

public class ReviewResDTO {
    // 리뷰 하나의 정보
    @Builder
    public record ReviewPreviewDTO(
            String ownerNickname,
            BigDecimal rating,
            String body,
            LocalDateTime createdAt
    ){}

    @Builder
    public record ReviewPreviewListDTO(
            // 리뷰 목록이기 때문에 리뷰 정보들의 목록이 필요
            List<ReviewPreviewDTO> reviewList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ){}
}
