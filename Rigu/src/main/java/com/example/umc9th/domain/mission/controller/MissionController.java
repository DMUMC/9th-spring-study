package com.example.umc9th.domain.mission.controller;

import com.example.umc9th.domain.mission.dto.MissionChallengeRequest;
import com.example.umc9th.domain.mission.dto.MissionChallengeResponse;
import com.example.umc9th.domain.mission.service.MissionChallengeService;
import com.example.umc9th.global.apiResponse.ApiResponse;
import com.example.umc9th.global.apiResponse.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MissionController {

    private final MissionChallengeService missionChallengeService;

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
}
