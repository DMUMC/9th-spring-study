package com.example.dobee.domain.review.controller;

import com.example.dobee.domain.review.dto.ReviewDto;
import com.example.dobee.domain.review.service.ReviewService;
import com.example.dobee.global.annotation.PageRange;
import com.example.dobee.global.code.SuccessCode;
import com.example.dobee.global.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "리뷰 API", description = "리뷰 관련 API")
@Validated
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

    @Operation(summary = "내가 작성한 리뷰 목록 조회", description = "내가 작성한 리뷰 목록을 10개씩 페이징하여 조회합니다.")
    @GetMapping("/my")
    public ApiResponse<Page<ReviewDto.Response>> getMyReviews(
            @Parameter(hidden = true) @RequestHeader("Member-Id") Long memberId,
            @Parameter(description = "페이지 번호 (1부터 시작)", required = true, example = "1") @PageRange @RequestParam(name = "page") Integer page
    ){
        Pageable pageable = PageRequest.of(page - 1, 10);
        Page<ReviewDto.Response> myReviews = reviewService.getMyReviews(memberId, null, null, pageable);
        return ApiResponse.onSuccess(SuccessCode.OK, myReviews);
    }
}
