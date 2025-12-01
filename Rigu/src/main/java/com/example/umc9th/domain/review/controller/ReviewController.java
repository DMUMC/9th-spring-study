// com.example.umc9th.domain.review.controller.ReviewController

package com.example.umc9th.domain.review.controller;

import com.example.umc9th.domain.review.dto.MyReviewPageResponse;
import com.example.umc9th.domain.review.service.ReviewQueryService;
import com.example.umc9th.global.apiResponse.ApiResponse;
import com.example.umc9th.global.apiResponse.code.GeneralSuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewQueryService reviewQueryService;

    @Operation(
            summary = "내가 작성한 리뷰 목록 조회 API (개발 중)",
            description = "특정 회원이 작성한 리뷰들을 조건에 맞게 페이징하여 조회합니다. 한 페이지에 10개를 반환합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.
                    ApiResponse(responseCode = "200", description = "성공")
    })
    @GetMapping("/my-reviews")
    public ApiResponse<MyReviewPageResponse> getMyReviews(
            @RequestParam Long memberId,
            @RequestParam(required = false) String storeName,
            @RequestParam(required = false) Integer minStar,
            @RequestParam(required = false) Integer maxStar,
            @RequestParam(defaultValue = "1") Integer page
    ) {
        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                reviewQueryService.getMyReviews(memberId, storeName, minStar, maxStar, page)
        );
    }
}
