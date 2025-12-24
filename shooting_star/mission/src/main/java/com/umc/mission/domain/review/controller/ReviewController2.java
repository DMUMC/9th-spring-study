package com.umc.mission.domain.review.controller;

import com.umc.mission.domain.review.converter.ReviewConverter;
import com.umc.mission.domain.review.dto.ReviewResDTO;
import com.umc.mission.domain.review.dto.StoreReviewReqDTO;
import com.umc.mission.domain.review.dto.StoreReviewResDTO.CreateReviewResultDTO;
import com.umc.mission.domain.review.entity.Review;
import com.umc.mission.domain.review.exception.code.ReviewSuccessCode;
import com.umc.mission.domain.review.service.ReviewCommandService;
import com.umc.mission.domain.review.service.ReviewQueryService;
import com.umc.mission.global.annotation.CheckPage;
import com.umc.mission.global.apiPayload.ApiResponse;
import com.umc.mission.global.apiPayload.code.BaseSuccessCode;
import com.umc.mission.global.apiPayload.code.GeneralSuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReviewController2 implements ReviewControllerDocs {
    private final ReviewCommandService reviewCommandService;
    private final ReviewQueryService reviewQueryService;

    // 가게에 리뷰 추가하기 api
    @PostMapping("/stores/{storeId}")
    public ApiResponse<CreateReviewResultDTO> createReview(
            @PathVariable(name = "storeId") Long storeId,
            @RequestBody @Valid StoreReviewReqDTO.CreateReviewDTO request
    ) {

        Review review = reviewCommandService.createReview(1L, storeId, request);

        BaseSuccessCode code = GeneralSuccessCode.OK;
        return ApiResponse.onSuccess(
                code,
                ReviewConverter.toCreateResultDTO(review));
    }

    // 가게의 리뷰 목록 조회
    @Override
    @GetMapping("store/{storeId}/reviews")
    public ApiResponse<ReviewResDTO.ReviewPreviewListDTO> getStoreReviewList(
            @PathVariable Long storeId,
            @RequestParam Integer page
    ) {
        Page<Review> reviewPage = reviewQueryService.findReviewByStoreId(storeId, page);

        return ApiResponse.onSuccess(
                ReviewSuccessCode.FOUND,
                ReviewConverter.toReviewPreviewListDTO(reviewPage));
    }

    // 나의 리뷰 조회
    @Override
    @GetMapping("member/{memberId}/reviews")
    public ApiResponse<ReviewResDTO.ReviewPreviewListDTO> getMyReviewList(
            @PathVariable Long memberId,
            @CheckPage Integer page
    ) {
        Page<Review> reviewPage = reviewQueryService.findReviewByMemberId(memberId, page);

        return ApiResponse.onSuccess(
                ReviewSuccessCode.FOUND,
                ReviewConverter.toReviewPreviewListDTO(reviewPage)
        );
    }
}