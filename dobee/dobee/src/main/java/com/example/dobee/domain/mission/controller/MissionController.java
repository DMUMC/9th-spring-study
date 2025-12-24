package com.example.dobee.domain.mission.controller;

import com.example.dobee.domain.mission.converter.MissionConverter;
import com.example.dobee.domain.mission.dto.MissionDto;
import com.example.dobee.domain.mission.entity.Mission;
import com.example.dobee.domain.mission.entity.mapping.MemberMission;
import com.example.dobee.domain.mission.exception.code.MissionSuccessCode;
import com.example.dobee.domain.mission.service.MissionService;
import com.example.dobee.global.annotation.CheckPage;
import com.example.dobee.global.annotation.ExistMember;
import com.example.dobee.global.annotation.ExistMemberMission;
import com.example.dobee.global.annotation.ExistStore;
import com.example.dobee.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/missions")
public class MissionController implements MissionControllerDocs {

    private final MissionService missionService;

    @Override
    @PostMapping
    public ApiResponse<MissionDto.AddMissionResDto> addMission(@RequestBody @Valid MissionDto.AddMissionReqDto request) {
        Mission newMission = missionService.addMission(request);
        return ApiResponse.onSuccess(MissionSuccessCode.MISSION_ADD_SUCCESS, MissionConverter.toAddMissionResDto(newMission));
    }

    @Override
    @PostMapping("/challenge")
    public ApiResponse<MissionDto.ChallengeMissionResDto> challengeMission(@RequestBody @Valid MissionDto.ChallengeMissionReqDto request) {
        MemberMission newMemberMission = missionService.challengeMission(request);
        return ApiResponse.onSuccess(MissionSuccessCode.MISSION_CHALLENGE_SUCCESS, MissionConverter.toChallengeMissionResDto(newMemberMission));
    }

    @Override
    @GetMapping("/stores/{storeId}")
    public ApiResponse<MissionDto.MissionPreviewListDto> getMissionList(
            @ExistStore @PathVariable(name = "storeId") Long storeId,
            @CheckPage @RequestParam(name = "page") Integer page) {
        MissionDto.MissionPreviewListDto missionList = missionService.getMissionList(storeId, page);
        return ApiResponse.onSuccess(MissionSuccessCode.GET_MISSION_LIST_SUCCESS, missionList);
    }

    @Override
    @GetMapping("/my-challenging")
    public ApiResponse<MissionDto.MyChallengingMissionListDto> getMyChallengingMissions(
            @ExistMember @RequestParam(name = "memberId") Long memberId,
            @CheckPage @RequestParam(name = "page") Integer page) {
        MissionDto.MyChallengingMissionListDto missionList = missionService.getMyChallengingMissions(memberId, page);
        return ApiResponse.onSuccess(MissionSuccessCode.GET_MY_CHALLENGING_MISSIONS_SUCCESS, missionList);
    }

    @Override
    @PatchMapping("/complete/{memberMissionId}")
    public ApiResponse<MissionDto.CompleteMissionResDto> completeMission(
            @ExistMemberMission @PathVariable(name = "memberMissionId") Long memberMissionId) {
        MemberMission memberMission = missionService.completeMission(memberMissionId);
        return ApiResponse.onSuccess(MissionSuccessCode.MISSION_COMPLETE_SUCCESS, MissionConverter.toCompleteMissionResDto(memberMission));
    }
}