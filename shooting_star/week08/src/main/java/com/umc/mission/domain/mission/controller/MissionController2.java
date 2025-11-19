package com.umc.mission.domain.mission.controller;

import com.umc.mission.domain.mission.dto.MissionDto2;
import com.umc.mission.domain.mission.entity.MemberMission;
import com.umc.mission.domain.mission.service.MissionService;
import com.umc.mission.domain.mission.service.MissionServiceCommand;
import com.umc.mission.global.apiPayload.ApiResponse;
import com.umc.mission.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/missions")
@RequiredArgsConstructor
public class MissionController2 {
    private final MissionServiceCommand missionService;

    @PostMapping("/{missionId}/challenges")
    public ApiResponse<MissionDto2.ChallengeResponseDTO> challengeMission(
            @PathVariable Long missionId,
            @RequestBody MissionDto2.ChallengeRequestDTO request
    ){
        MemberMission memberMission = missionService.challengeMission(
                1L,
                missionId,
                request);

        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                MissionDto2.ChallengeResponseDTO.from(memberMission)
        );
    }
}
