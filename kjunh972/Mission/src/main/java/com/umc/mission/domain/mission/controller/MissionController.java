package com.umc.mission.domain.mission.controller;

import com.umc.mission.domain.mission.dto.MissionDto;
import com.umc.mission.domain.mission.service.MissionService;
import com.umc.mission.global.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/missions")
@RequiredArgsConstructor
@Tag(name = "미션 API", description = "미션 관련 API")
public class MissionController {

    private final MissionService missionService;

    @PostMapping("/challenges/without-lock")
    @Operation(summary = "미션 도전하기 (Lock 없음)", description = "사용자가 특정 미션을 도전 중인 미션에 추가합니다. (Lock 동시성 제어 없음)")
    public ApiResponse<Void> challengeMissionWithoutLock(
            @Parameter(description = "회원 ID") @RequestParam Long memberId,
            @Parameter(description = "미션 ID") @RequestParam Long missionId) {
        missionService.challengeMissionWithoutLock(memberId, missionId);
        return ApiResponse.ok();
    }

    @PostMapping("/challenges/with-lock")
    @Operation(summary = "미션 도전하기 (Lock 있음)", description = "사용자가 특정 미션을 도전 중인 미션에 추가합니다. (Lock 동시성 제어 포함)")
    public ApiResponse<Void> challengeMissionWithLock(
            @Parameter(description = "회원 ID") @RequestParam Long memberId,
            @Parameter(description = "미션 ID") @RequestParam Long missionId) {
        missionService.challengeMissionWithLock(memberId, missionId);
        return ApiResponse.ok();
    }

    @GetMapping("/region/{regionId}")
    public ApiResponse<Page<MissionDto.Response>> getActiveMissionsByRegion(
            @PathVariable Long regionId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ApiResponse.ok(missionService.getMissionsRegion(regionId, page, size));
    }

    @GetMapping("/region/{regionId}/with-store")
    public ApiResponse<Page<MissionDto.Response>> getActiveMissionsByRegionWithStore(
            @PathVariable Long regionId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ApiResponse.ok(missionService.getMissionsRegionWithStore(regionId, page, size));
    }

    @GetMapping("/region/{regionId}/available")
    public ApiResponse<Page<MissionDto.Response>> getAvailableMissionsByRegionAndMember(
            @PathVariable Long regionId,
            @RequestParam Long memberId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ApiResponse.ok(missionService.getMissionsAvailable(regionId, memberId, page, size));
    }

    @GetMapping("/status/{status}")
    public ApiResponse<Page<MissionDto.Response>> getMissionsByStatus(
            @PathVariable String status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ApiResponse.ok(missionService.getMissionsByStatus(status, page, size));
    }
}