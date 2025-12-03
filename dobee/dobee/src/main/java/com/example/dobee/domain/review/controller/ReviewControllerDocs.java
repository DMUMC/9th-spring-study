package com.example.dobee.domain.review.controller;

import com.example.dobee.domain.review.dto.ReviewDto;
import com.example.dobee.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Review", description = "리뷰 관련 API")
public interface ReviewControllerDocs {

    @Operation(summary = "내가 작성한 리뷰 목록 조회 API", description = "내가 작성한 리뷰 목록을 필터링 및 페이징하여 조회합니다.")
    @Parameters({
            @Parameter(name = "memberId", description = "회원 ID", required = true),
            @Parameter(name = "page", description = "페이지 번호 (1부터 시작)", required = true),
            @Parameter(name = "storeName", description = "가게 이름 (필터링 조건)"),
            @Parameter(name = "rating", description = "별점 (필터링 조건, 예: 4 입력 시 4점대 조회)")
    })
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "REVIEW_001", description = "내가 작성한 리뷰 조회가 완료되었습니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    })
    ApiResponse<ReviewDto.ReviewPreViewListResDto> getMyReviews(Long memberId, Integer page, String storeName, Integer rating);

    @Operation(summary = "리뷰 추가 API", description = "특정 가게에 리뷰를 추가합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "REVIEW_002", description = "리뷰 작성이 완료되었습니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    })
    ApiResponse<ReviewDto.AddReviewResDto> addReview(@RequestBody @Valid ReviewDto.AddReviewReqDto request);
}
