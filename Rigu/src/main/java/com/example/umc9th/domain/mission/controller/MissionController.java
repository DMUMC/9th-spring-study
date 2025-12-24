package com.example.umc9th.domain.mission.controller;

import com.example.umc9th.domain.mission.dto.MissionChallengeRequest;
import com.example.umc9th.domain.mission.dto.MissionChallengeResponse;
import com.example.umc9th.domain.mission.dto.MyMissionPageResponse;
import com.example.umc9th.domain.mission.dto.StoreMissionPageResponse;
import com.example.umc9th.domain.mission.service.MissionChallengeService;
import com.example.umc9th.domain.mission.service.MissionQueryService;
import com.example.umc9th.global.apiResponse.ApiResponse;
import com.example.umc9th.global.apiResponse.code.GeneralSuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MissionController {

    private final MissionChallengeService missionChallengeService;
    private final MissionQueryService missionQueryService;

    @Operation(
            summary = "미션 도전하기 API (개발 중)",
            description = "특정 가게의 미션에 회원이 도전하는 API입니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "201",
                    description = "미션 도전 성공"
            )
    })
    @PostMapping("/stores/{storeId}/missions/challenge")
    public ApiResponse<MissionChallengeResponse> challengeMission(
            @PathVariable Long storeId,
            @RequestBody MissionChallengeRequest request
    ) {
        return ApiResponse.onSuccess(
                GeneralSuccessCode.CREATED,
                missionChallengeService.addChallenge(storeId, request)
        );
    }

    @Operation(
            summary = "특정 가게의 미션 목록 조회 API (개발 중)",
            description = "특정 가게에 등록된 미션들을 페이징하여 조회합니다. 한 페이지에 10개를 반환합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "가게 미션 목록 조회 성공"
            )
    })
    @GetMapping("/stores/{storeId}/missions")
    public ApiResponse<StoreMissionPageResponse> getStoreMissions(
            @PathVariable Long storeId,
            @RequestParam(defaultValue = "1") Integer page
    ) {
        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                missionQueryService.getStoreMissions(storeId, page)
        );
    }

    @Operation(
            summary = "내가 진행중인 미션 목록 조회 API (개발 중)",
            description = "특정 회원이 진행중(IN_PROGRESS)인 미션들을 페이징하여 조회합니다. 한 페이지에 10개를 반환합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "내 진행중 미션 목록 조회 성공"
            )
    })
    @GetMapping("/members/{memberId}/missions/in-progress")
    public ApiResponse<MyMissionPageResponse> getMyOngoingMissions(
            @PathVariable Long memberId,
            @RequestParam(defaultValue = "1") Integer page
    ) {
        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                missionQueryService.getMyOngoingMissions(memberId, page)
        );
    }
}
