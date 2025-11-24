package com.umc.mission.domain.review.controller;

import com.umc.mission.domain.review.dto.ReviewResDTO;
import com.umc.mission.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

public interface ReviewControllerDocs {
    // 가게의 리뷰 찾기
    @Operation(
            summary = "가게의 리뷰 목록 조회 API",
            description = "특정 가게의 리뷰를 모두 조회합니다. 페이지네이션으로 제공합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    @Parameters({
            @Parameter(name = "storeId", description = "가게의 id입니다."),
            @Parameter(name = "page", description = "페이지 번호입니다. 1부터 시작합니다.")
    })
    ApiResponse<ReviewResDTO.ReviewPreviewListDTO> getStoreReviewList(
            @PathVariable Long storeId,
            @RequestParam Integer page
    );

    // 나의 리뷰 찾기
    @Operation(
            summary = "회원의 리뷰 목록 조회 API",
            description = "특정 회원의 리뷰를 모두 조회합니다. 페이지네이션으로 제공합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    @Parameters({
            @Parameter(name = "memberId", description = "회원의 id입니다."),
            @Parameter(name = "page", description = "페이지 번호입니다. 1부터 시작합니다.")
    })
    ApiResponse<ReviewResDTO.ReviewPreviewListDTO> getMyReviewList(
            @PathVariable Long memberId,
            @RequestParam Integer page
    );
}
