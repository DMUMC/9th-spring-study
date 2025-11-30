package com.umc.controller;

import com.umc.annotation.ValidPage;
import com.umc.apiController.ApiResponse;
import com.umc.apiController.code.GeneralResponseCode;
import com.umc.dto.request.ReviewRequestDto;
import com.umc.dto.response.ReviewResponseDTO;
import com.umc.dto.response.SimpleReviewResponseDto;
import com.umc.exception.InvalidPageException;
import com.umc.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
@Validated
@Tag(name = "리뷰 API", description = "가게 리뷰 관련 API")
public class ReviewController {
    
    private final ReviewService reviewService;
    
    @PostMapping
    @Operation(summary = "리뷰 작성", description = "가게에 리뷰를 작성합니다. (하드코딩된 회원 ID: 1)")
    public ApiResponse<SimpleReviewResponseDto> createReview(
            @Valid @RequestBody ReviewRequestDto requestDto
    ) {
        SimpleReviewResponseDto response = reviewService.createReview(requestDto);
        return ApiResponse.onSuccess(response);
    }
    
    @GetMapping("/stores/{storeId}")
    @Operation(summary = "가게 리뷰 목록 조회", description = "특정 가게의 모든 리뷰를 조회합니다.")
    public ApiResponse<List<SimpleReviewResponseDto>> getStoreReviews(
            @Parameter(description = "가게 ID", example = "1")
            @PathVariable Long storeId
    ) {
        List<SimpleReviewResponseDto> reviews = reviewService.getStoreReviews(storeId);
        return ApiResponse.onSuccess(reviews);
    }
    
    @GetMapping("/my")
    @Operation(summary = "내 리뷰 목록 조회", description = "내가 작성한 모든 리뷰를 조회합니다. (하드코딩된 회원 ID: 1)")
    public ApiResponse<List<SimpleReviewResponseDto>> getMyReviews() {
        List<SimpleReviewResponseDto> reviews = reviewService.getMyReviews();
        return ApiResponse.onSuccess(reviews);
    }
    
    @GetMapping("/members/{memberId}")
    @Operation(summary = "내가 작성한 리뷰 목록 (페이징)", description = "특정 회원이 작성한 리뷰 목록을 페이징하여 조회합니다. 한 페이지에 10개씩 조회됩니다.")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "조회 성공",
                    content = @Content(schema = @Schema(implementation = ReviewResponseDTO.ReviewPreviewListDTO.class))
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "페이지 번호가 1 미만인 경우",
                    content = @Content(schema = @Schema(implementation = ApiResponse.class))
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404",
                    description = "회원을 찾을 수 없는 경우",
                    content = @Content(schema = @Schema(implementation = ApiResponse.class))
            )
    })
    public ApiResponse<ReviewResponseDTO.ReviewPreviewListDTO> getMyReviewsPaged(
            @Parameter(description = "회원 ID", example = "1")
            @PathVariable Long memberId,
            @Parameter(description = "페이지 번호 (1 이상)", example = "1")
            @RequestParam @ValidPage Integer page
    ) {
        if (page == null || page < 1) {
            throw new InvalidPageException(GeneralResponseCode.INVALID_PAGE);
        }
        ReviewResponseDTO.ReviewPreviewListDTO reviews = reviewService.getMyReviewsPaged(memberId, page);
        return ApiResponse.onSuccess(reviews);
    }
}
