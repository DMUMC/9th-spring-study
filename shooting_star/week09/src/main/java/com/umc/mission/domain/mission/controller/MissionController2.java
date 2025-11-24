package com.umc.mission.domain.mission.controller;

import com.umc.mission.domain.member.exception.code.MemberSuccessCode;
import com.umc.mission.domain.mission.converter.MemberMissionConverter;
import com.umc.mission.domain.mission.converter.MissionConverter;
import com.umc.mission.domain.mission.dto.MemberMissionResDTO;
import com.umc.mission.domain.mission.dto.MissionDto2;
import com.umc.mission.domain.mission.dto.MissionResDTO;
import com.umc.mission.domain.mission.entity.MemberMission;
import com.umc.mission.domain.mission.entity.Mission;
import com.umc.mission.domain.mission.exception.code.MissionSuccessCode;
import com.umc.mission.domain.mission.service.MissionQueryService;
import com.umc.mission.domain.mission.service.MissionServiceCommand;
import com.umc.mission.global.annotation.CheckPage;
import com.umc.mission.global.apiPayload.ApiResponse;
import com.umc.mission.global.apiPayload.code.GeneralSuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MissionController2 implements MissionControllerDocs{
    private final MissionServiceCommand missionService;
    private final MissionQueryService missionQueryService;

    @PostMapping("/mission/{missionId}/challenges")
    @Operation(
            summary = "미션 도전하기",
            description = "사용자가 특정 미션을 도전 중인 미션에 추가합니다."
    )
    public ApiResponse<MissionDto2.ChallengeResponseDTO> challengeMission(
            @PathVariable(name = "missionId") Long missionId,
            @RequestBody @Valid MissionDto2.ChallengeRequestDTO request
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

    @GetMapping("/store/{storeId}/missions")
    public ApiResponse<MissionResDTO.MissionPreviewListDTO> getMissionListByStore(
            @PathVariable Long storeId,
            @CheckPage Integer page
    ){
        Page<Mission> missionPage = missionQueryService.getMissionListByStoreId(storeId, page);

        return ApiResponse.onSuccess(
                MissionSuccessCode.FOUND,
                MissionConverter.toMissionPreviewListDTO(missionPage)
        );
    }

    @GetMapping("/member/{memberId}/missions/challenging")
    public ApiResponse<MemberMissionResDTO.ChallengingMissionPreviewListDTO> getMyChallengingMissions(
            @PathVariable Long memberId,
            @CheckPage Integer page
    ){
        Page<MemberMission> memberMissionPage = missionQueryService.getMyChallengingMissions(memberId, page);

        return ApiResponse.onSuccess(
                MemberSuccessCode.FOUND,
                MemberMissionConverter.toChallengingMissionPreviewListDTO(memberMissionPage)
        );
    }
}
