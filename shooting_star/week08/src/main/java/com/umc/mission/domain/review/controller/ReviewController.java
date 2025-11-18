package com.umc.mission.domain.review.controller;

import com.umc.mission.domain.review.dto.ReviewDto;
import com.umc.mission.domain.review.service.ReviewService;
import com.umc.mission.global.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public ApiResponse<Void> createReview(@RequestBody ReviewDto.Request request) {
        reviewService.createReview(request.getMemberId(), request.getStoreId(),
                                    request.getRating(), request.getContent());
        return ApiResponse.ok();
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

    // 내가 작성한 리뷰 동적 조회 api
    @GetMapping("member/{memberId}/search")
    public ApiResponse<List<ReviewDto.Response>> searchMyReviews(
            @PathVariable Long memberId,
            @RequestParam(required = false) Long storeId,
            @RequestParam(required = false) Integer rating
    ){
        List<ReviewDto.Response> results = reviewService.searchMyReviews(
                memberId, storeId, rating
        );

        return ApiResponse.ok(results);
    }
}