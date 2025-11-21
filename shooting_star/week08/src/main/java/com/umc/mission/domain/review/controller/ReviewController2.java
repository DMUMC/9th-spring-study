package com.umc.mission.domain.review.controller;

import com.umc.mission.domain.review.converter.ReviewConverter;
import com.umc.mission.domain.review.dto.StoreReviewReqDTO;
import com.umc.mission.domain.review.dto.StoreReviewResDTO.CreateReviewResultDTO;
import com.umc.mission.domain.review.entity.Review;
import com.umc.mission.domain.review.service.ReviewCommandService;
import com.umc.mission.global.apiPayload.ApiResponse;
import com.umc.mission.global.apiPayload.code.BaseSuccessCode;
import com.umc.mission.global.apiPayload.code.GeneralSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class ReviewController2 {
    private final ReviewCommandService reviewCommandService;

    // 가게에 리뷰 추가하기 api
    @PostMapping("/{storeId}/review")
    public ApiResponse<CreateReviewResultDTO> createReview(
            @PathVariable(name = "storeId") Long storeId,
            @RequestBody @Valid StoreReviewReqDTO.CreateReviewDTO request
    ){

        Review review = reviewCommandService.createReview(1L, storeId, request);

        BaseSuccessCode code = GeneralSuccessCode.OK;
        return ApiResponse.onSuccess(
                code,
                ReviewConverter.toCreateResultDTO(review));
    }
}
