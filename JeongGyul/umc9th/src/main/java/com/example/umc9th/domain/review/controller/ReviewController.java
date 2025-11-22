package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.dto.req.ReviewReqDTO;
import com.example.umc9th.domain.review.dto.res.ReviewResDTO;
import com.example.umc9th.domain.review.service.command.ReviewCommandService;
import com.example.umc9th.domain.review.service.query.ReviewQueryService;
import com.example.umc9th.global.ApiPayload.ApiResponse;
import com.example.umc9th.global.ApiPayload.code.GeneralSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {
    private final ReviewQueryService reviewQueryService;
    private final ReviewCommandService reviewCommandService;

    @PostMapping
    public ApiResponse<ReviewResDTO.AddDTO> addReview(
            @RequestBody @Valid ReviewReqDTO.AddDTO dto
    ) {
        GeneralSuccessCode code = GeneralSuccessCode.CREATED;
        return ApiResponse.onSuccess(code, reviewCommandService.addReview(dto));
    }

    @GetMapping("/my-reviews")
    public ApiResponse<List<ReviewResDTO.MyReviewDTO>> getMyReviews(
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