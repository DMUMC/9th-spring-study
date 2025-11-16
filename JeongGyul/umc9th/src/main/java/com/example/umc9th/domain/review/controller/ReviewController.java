package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.dto.res.ReviewResDTO;
import com.example.umc9th.domain.review.service.ReviewQueryService;
import com.example.umc9th.global.ApiPayload.ApiResponse;
import com.example.umc9th.global.ApiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {
    private final ReviewQueryService reviewQueryService;

    @GetMapping("/my-reviews")
    public ApiResponse<List<ReviewResDTO.ReviewDTO>> getMyReviews(
            // API 테스트를 위한 임시 코드
            // 로그인 기능 구현하면 수정할 것
            @RequestParam Long memberId,
            @RequestParam(required = false) Long storeId,
            @RequestParam(required = false) Integer rating
    ) {

        GeneralSuccessCode code = GeneralSuccessCode.OK;
        return ApiResponse.onSuccess(
                code,
                reviewQueryService.searchReview(memberId, storeId, rating)
        );
    }
}