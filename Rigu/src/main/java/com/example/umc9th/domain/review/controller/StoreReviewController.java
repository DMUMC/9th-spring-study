package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.dto.ReviewCreateRequest;
import com.example.umc9th.domain.review.dto.ReviewCreateResponse;
import com.example.umc9th.domain.review.service.ReviewCommandService;
import com.example.umc9th.global.apiResponse.ApiResponse;
import com.example.umc9th.global.apiResponse.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class StoreReviewController {

    private final ReviewCommandService reviewCommandService;

    @PostMapping("/stores/{storeId}/reviews")
    public ApiResponse<ReviewCreateResponse> addReview(
            @PathVariable Long storeId,
            @RequestBody ReviewCreateRequest request
    ) {
        return ApiResponse.onSuccess(
                GeneralSuccessCode.CREATED,
                reviewCommandService.addReview(storeId, request)
        );
    }
}
