package com.example.dobee.domain.review.controller;

import com.example.dobee.domain.review.dto.ReviewDto;
import com.example.dobee.domain.review.service.ReviewService;
import com.example.dobee.global.code.SuccessCode;
import com.example.dobee.global.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public ApiResponse<Void> createReview(@RequestBody ReviewDto.Request request) {
        reviewService.createReview(request.getMemberId(), request.getStoreId(),
                request.getRating(), request.getContent());
        return ApiResponse.onSuccess(SuccessCode.CREATED);
    }

    @GetMapping("/member/{memberId}")
    public ApiResponse<Page<ReviewDto.Response>> getReviewsMember(
            @PathVariable Long memberId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ApiResponse.onSuccess(SuccessCode.OK, reviewService.getReviewsMember(memberId, page, size));
    }

    @GetMapping("/store/{storeId}")
    public ApiResponse<Page<ReviewDto.Response>> getReviewsStore(
            @PathVariable Long storeId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ApiResponse.onSuccess(SuccessCode.OK, reviewService.getReviewsActive(storeId, page, size));
    }

    @GetMapping("/member/{memberId}/active")
    public ApiResponse<Page<ReviewDto.Response>> getReviewsActive(
            @PathVariable Long memberId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ApiResponse.onSuccess(SuccessCode.OK, reviewService.getReviewsActive(memberId, page, size));
    }

    @GetMapping("/member/{memberId}/count")
    public ApiResponse<Long> getReviewCount(@PathVariable Long memberId) {
        return ApiResponse.onSuccess(SuccessCode.OK, reviewService.getReviewCount(memberId));
    }

    @GetMapping("/my")
    public ApiResponse<Page<ReviewDto.Response>> getMyReviews(
            @RequestHeader("Member-Id") Long memberId,
            @RequestParam(required = false) String storeName,
            @RequestParam(required = false) Integer rating,
            Pageable pageable
    ){
        Page<ReviewDto.Response> myReviews = reviewService.getMyReviews(memberId, storeName, rating, pageable);
        return ApiResponse.onSuccess(SuccessCode.OK, myReviews);
    }
}
