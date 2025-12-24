package com.umc.mission.domain.mission.controller;

import com.umc.mission.domain.mission.dto.MissionDto;
import com.umc.mission.domain.mission.service.MissionService;
import com.umc.mission.global.response.ApiResponse;
import com.umc.mission.global.validation.annotation.CheckPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/missions")
@RequiredArgsConstructor
@Validated
public class MissionController {

    private final MissionService missionService;

    @PostMapping("/challenges/without-lock")
    public ApiResponse<Void> challengeMissionWithoutLock(
            @RequestParam Long memberId,
            @RequestParam Long missionId) {
        missionService.challengeMissionWithoutLock(memberId, missionId);
        return ApiResponse.ok();
    }

    // 3. 가게에 미션 추가하기 API
    @PostMapping
    public ApiResponse<Long> createMission(@RequestBody @Valid MissionDto.Request request) {
        Long missionId = missionService.createMission(request);
        return ApiResponse.ok(missionId);
    }

    @PostMapping("/challenges/with-lock")
    public ApiResponse<Void> challengeMissionWithLock(
            @RequestParam Long memberId,
            @RequestParam Long missionId) {
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

    @GetMapping("/store/{storeId}")
    @Operation(summary = "특정 가게의 미션 목록 조회 API", description = "특정 가게에 등록된 미션 목록을 조회합니다. (페이징 포함)")
    @Parameters({
            @Parameter(name = "storeId", description = "가게의 ID"),
            @Parameter(name = "page", description = "페이지 번호 (1부터 시작)")
    })
    public ApiResponse<MissionDto.PageResponse> getMissionsByStore(
            @PathVariable Long storeId,
            @CheckPage @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<MissionDto.Response> missionPage = missionService.getMissionsByStore(storeId, page - 1, size);
        return ApiResponse.ok(MissionDto.PageResponse.from(missionPage));
    }
}