package com.example.dobee.domain.review.controller;

import com.example.dobee.domain.review.converter.ReviewConverter;
import com.example.dobee.domain.review.dto.ReviewDto;
import com.example.dobee.domain.review.entity.Review;
import com.example.dobee.domain.review.exception.code.ReviewSuccessCode;
import com.example.dobee.domain.review.service.command.ReviewCommandService;
import com.example.dobee.domain.review.service.query.ReviewQueryService;
import com.example.dobee.global.annotation.CheckPage;
import com.example.dobee.global.annotation.ExistMember;
import com.example.dobee.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController implements ReviewControllerDocs {

    private final ReviewCommandService reviewCommandService;
    private final ReviewQueryService reviewQueryService;

    @Override
    @GetMapping("/my")
    public ApiResponse<ReviewDto.ReviewPreViewListResDto> getMyReviews(
            @ExistMember @RequestParam(name = "memberId") Long memberId,
            @CheckPage @RequestParam(name = "page") Integer page,
            @RequestParam(name = "storeName", required = false) String storeName,
            @RequestParam(name = "rating", required = false) Integer rating) {
        ReviewDto.ReviewPreViewListResDto myReviews = reviewQueryService.getMyReviews(memberId, page, storeName, rating);
        return ApiResponse.onSuccess(ReviewSuccessCode.GET_MY_REVIEWS_SUCCESS, myReviews);
    }

    @Override
    @PostMapping
    public ApiResponse<ReviewDto.AddReviewResDto> addReview(@RequestBody @Valid ReviewDto.AddReviewReqDto request) {
        Review newReview = reviewCommandService.addReview(request);
        return ApiResponse.onSuccess(ReviewSuccessCode.REVIEW_ADD_SUCCESS, ReviewConverter.toAddReviewResDto(newReview));
    }
}
