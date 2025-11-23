package com.umc.controller;

import com.umc.apiController.ApiResponse;
import com.umc.dto.request.ReviewRequestDto;
import com.umc.dto.response.ReviewResponseDto;
import com.umc.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
@Tag(name = "리뷰 API", description = "가게 리뷰 관련 API")
public class ReviewController {
    
    private final ReviewService reviewService;
    
    @PostMapping
    @Operation(summary = "리뷰 작성", description = "가게에 리뷰를 작성합니다. (하드코딩된 회원 ID: 1)")
    public ApiResponse<ReviewResponseDto> createReview(
            @Valid @RequestBody ReviewRequestDto requestDto
    ) {
        ReviewResponseDto response = reviewService.createReview(requestDto);
        return ApiResponse.onSuccess(response);
    }
    
    @GetMapping("/stores/{storeId}")
    @Operation(summary = "가게 리뷰 목록 조회", description = "특정 가게의 모든 리뷰를 조회합니다.")
    public ApiResponse<List<ReviewResponseDto>> getStoreReviews(
            @Parameter(description = "가게 ID", example = "1")
            @PathVariable Long storeId
    ) {
        List<ReviewResponseDto> reviews = reviewService.getStoreReviews(storeId);
        return ApiResponse.onSuccess(reviews);
    }
    
    @GetMapping("/my")
    @Operation(summary = "내 리뷰 목록 조회", description = "내가 작성한 모든 리뷰를 조회합니다. (하드코딩된 회원 ID: 1)")
    public ApiResponse<List<ReviewResponseDto>> getMyReviews() {
        List<ReviewResponseDto> reviews = reviewService.getMyReviews();
        return ApiResponse.onSuccess(reviews);
    }
}
