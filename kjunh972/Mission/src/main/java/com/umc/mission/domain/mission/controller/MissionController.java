package com.umc.mission.domain.mission.controller;

import com.umc.mission.domain.mission.dto.MemberMissionDto;
import com.umc.mission.domain.mission.dto.MissionDto;
import com.umc.mission.domain.mission.service.MissionService;
import com.umc.mission.global.response.ApiResponse;
import com.umc.mission.global.validation.ValidPage;
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

    @GetMapping("/store/{storeId}")
    @Operation(summary = "특정 가게의 미션 목록 조회", description = "특정 가게의 활성화된 미션을 페이징으로 조회합니다. (10개씩)")
    public ApiResponse<MissionDto.PageResponse> getMissionsByStore(
            @Parameter(description = "가게 ID") @PathVariable Long storeId,
            @Parameter(description = "페이지 번호 (1 이상)") @ValidPage @RequestParam int page) {
        return ApiResponse.ok(missionService.getMissionsByStore(storeId, page));
    }

    @GetMapping("/my/ongoing")
    @Operation(summary = "내가 진행중인 미션 목록 조회", description = "특정 회원이 진행중인 미션을 페이징으로 조회합니다. (10개씩)")
    public ApiResponse<MemberMissionDto.PageResponse> getMyOngoingMissions(
            @Parameter(description = "회원 ID") @RequestParam Long memberId,
            @Parameter(description = "페이지 번호 (1 이상)") @ValidPage @RequestParam int page) {
        return ApiResponse.ok(missionService.getMyOngoingMissions(memberId, page));
    }

    @PatchMapping("/{memberMissionId}/complete")
    @Operation(summary = "진행중인 미션 완료로 변경", description = "진행중인 미션을 완료 상태로 변경하고, 변경된 미션 정보를 반환합니다.")
    public ApiResponse<MemberMissionDto.Response> completeMission(
            @Parameter(description = "회원-미션 ID") @PathVariable Long memberMissionId) {
        return ApiResponse.ok(missionService.completeMission(memberMissionId));
    }
}