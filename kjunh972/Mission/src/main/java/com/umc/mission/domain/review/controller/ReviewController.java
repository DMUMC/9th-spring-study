package com.umc.mission.domain.review.controller;

import com.umc.mission.domain.review.dto.ReviewDto;
import com.umc.mission.domain.review.entity.Review;
import com.umc.mission.domain.review.service.ReviewService;
import com.umc.mission.global.response.ApiResponse;
import com.umc.mission.global.validation.ValidPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
@Tag(name = "리뷰 API", description = "리뷰 관련 API")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    @Operation(summary = "가게에 리뷰 추가하기", description = "특정 가게에 리뷰를 작성합니다.")
    public ApiResponse<Void> createReview(@RequestBody ReviewDto.Request request) {
        reviewService.createReview(request.getMemberId(), request.getStoreId(),
                                    request.getRating(), request.getContent());
        return ApiResponse.ok();
    }

    @GetMapping("/my")
    @Operation(summary = "내가 작성한 리뷰 목록 조회", description = "특정 회원이 작성한 리뷰를 페이징으로 조회합니다. (10개씩)")
    public ApiResponse<ReviewDto.PageResponse> getMyReviews(
            @Parameter(description = "회원 ID") @RequestParam Long memberId,
            @Parameter(description = "페이지 번호 (1 이상)") @ValidPage @RequestParam int page) {
        return ApiResponse.ok(reviewService.getMyReviews(memberId, page));
    }

    @GetMapping("/member/{memberId}")
    public ApiResponse<Page<ReviewDto.Response>> getReviewsMember(
            @PathVariable Long memberId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ApiResponse.ok(reviewService.getReviewsMember(memberId, page, size));
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