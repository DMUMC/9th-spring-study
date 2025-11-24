package com.umc.mission.domain.review.controller;

import com.umc.mission.domain.review.dto.ReviewDto;
import com.umc.mission.domain.review.entity.Review;
import com.umc.mission.domain.review.service.ReviewService;
import com.umc.mission.global.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.umc.mission.global.validation.annotation.CheckPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;


import java.math.BigDecimal;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
@Validated
public class ReviewController {

    private final ReviewService reviewService;

    /**
     * week7 데이터가 없는 성공 응답
     */
    @PostMapping
    public ApiResponse<Void> createReview(@RequestBody ReviewDto.Request request) {
        reviewService.createReview(request.getMemberId(), request.getStoreId(),
                                    request.getRating(), request.getContent());
        return ApiResponse.ok();
    }

    @GetMapping("/member/{memberId}")
    @Operation(summary = "내가 작성한 리뷰 목록 조회 API", description = "특정 회원이 작성한 리뷰 목록을 조회합니다. (페이징 포함)")
    @Parameters({
            @Parameter(name = "memberId", description = "회원의 ID"),
            @Parameter(name = "page", description = "페이지 번호 (1부터 시작)")
    })
    public ApiResponse<ReviewDto.PageResponse> getReviewsMember(
            @PathVariable Long memberId,
            @CheckPage @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {

        // 1. Service에서 Entity Page를 가져옵니다 (page - 1 처리)
        Page<Review> reviewPage = reviewService.getReviewList(memberId, page - 1, size);

        // 2. DTO의 from 메서드를 통해 PageResponse로 변환하여 반환합니다
        return ApiResponse.ok(ReviewDto.PageResponse.from(reviewPage));
    }

    @GetMapping("/store/{storeId}")
    public ApiResponse<Page<ReviewDto.Response>> getReviewsStore(
            @PathVariable Long storeId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ApiResponse.ok(reviewService.getReviewsStore(storeId, page, size));
    }

    @GetMapping("/member/{memberId}/active")
    public ApiResponse<Page<ReviewDto.Response>> getReviewsActive(
            @PathVariable Long memberId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ApiResponse.ok(reviewService.getReviewsActive(memberId, page, size));
    }

    /**
     * week7 데이터가 있는 성공 응답
     */
    @GetMapping("/member/{memberId}/count")
    public ApiResponse<Long> getReviewCount(@PathVariable Long memberId) {
        return ApiResponse.ok(reviewService.getReviewCount(memberId));
    }

    /**
     * 내가 작성한 리뷰 필터링 조회 (가게별, 별점별)
     */
    @GetMapping("/member/{memberId}/filter")
    public ApiResponse<ReviewDto.PageResponse> getMyReviewsByFilters(
            @PathVariable Long memberId,
            @RequestParam(required = false) Long storeId,
            @RequestParam(required = false) BigDecimal minRating,
            @RequestParam(required = false) BigDecimal maxRating,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<Review> reviewPage = reviewService.getMyReviewsByFilters(memberId, storeId, minRating, maxRating, page, size);
        return ApiResponse.ok(ReviewDto.PageResponse.from(reviewPage));
    }


}