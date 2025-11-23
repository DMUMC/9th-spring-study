package com.umc.controller;

import com.umc.apiController.ApiResponse;
import com.umc.dto.request.MemberMissionRequestDto;
import com.umc.dto.response.MemberMissionResponseDto;
import com.umc.service.MemberMissionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/member-missions")
@RequiredArgsConstructor
@Tag(name = "미션 도전 API", description = "회원 미션 도전 관련 API")
public class MemberMissionController {
    
    private final MemberMissionService memberMissionService;
    
    @PostMapping
    @Operation(summary = "미션 도전하기", description = "가게의 미션을 도전 중인 미션에 추가합니다. (하드코딩된 회원 ID: 1)")
    public ApiResponse<MemberMissionResponseDto> challengeMission(
            @Valid @RequestBody MemberMissionRequestDto requestDto
    ) {
        MemberMissionResponseDto response = memberMissionService.challengeMission(requestDto);
        return ApiResponse.onSuccess(response);
    }
    
    @GetMapping("/challenging")
    @Operation(summary = "도전 중인 미션 목록 조회", description = "내가 현재 도전 중인 미션 목록을 조회합니다. (하드코딩된 회원 ID: 1)")
    public ApiResponse<List<MemberMissionResponseDto>> getMyChallengingMissions() {
        List<MemberMissionResponseDto> missions = memberMissionService.getMyChallengingMissions();
        return ApiResponse.onSuccess(missions);
    }
    
    @GetMapping("/completed")
    @Operation(summary = "완료한 미션 목록 조회", description = "내가 완료한 미션 목록을 조회합니다. (하드코딩된 회원 ID: 1)")
    public ApiResponse<List<MemberMissionResponseDto>> getMyCompletedMissions() {
        List<MemberMissionResponseDto> missions = memberMissionService.getMyCompletedMissions();
        return ApiResponse.onSuccess(missions);
    }
    
    @PatchMapping("/{memberMissionId}/complete")
    @Operation(summary = "미션 완료하기", description = "도전 중인 미션을 완료 상태로 변경합니다. (하드코딩된 회원 ID: 1)")
    public ApiResponse<MemberMissionResponseDto> completeMission(
            @Parameter(description = "회원 미션 ID", example = "1")
            @PathVariable Long memberMissionId
    ) {
        MemberMissionResponseDto response = memberMissionService.completeMission(memberMissionId);
        return ApiResponse.onSuccess(response);
    }
}
