package com.example.dobee.domain.mission.Controller;

import com.example.dobee.domain.mission.dto.MemberMissionDto;
import com.example.dobee.domain.mission.dto.MissionDto;
import com.example.dobee.domain.mission.service.MissionService;
import com.example.dobee.global.annotation.PageRange;
import com.example.dobee.global.code.SuccessCode;
import com.example.dobee.global.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/missions")
@RequiredArgsConstructor
@Validated
@Tag(name = "Mission", description = "미션 관련 API")
public class MissionController {

    private final MissionService missionService;

    @PostMapping("/create/{storeId}")
    public ApiResponse<MissionDto.Response> addMissionToStore(
            @PathVariable Long storeId,
            @Valid @RequestBody MissionDto.AddMissionRequest request) {
        MissionDto.Response response = missionService.addMission(storeId, request);
        return ApiResponse.onSuccess(SuccessCode.CREATED, response);
    }

    @PostMapping("/challenges/without-lock")
    public ApiResponse<MemberMissionDto.Response> challengeMissionWithoutLock(
            @RequestParam Long memberId,
            @RequestParam Long missionId) {
        missionService.challengeMissionWithoutLock(memberId, missionId);
        return ApiResponse.onSuccess(SuccessCode.CREATED);
    }

    @PostMapping("/challenges/with-lock")
    public ApiResponse<Void> challengeMissionWithLock(
            @RequestParam Long memberId,
            @RequestParam Long missionId) {
        missionService.challengeMissionWithLock(memberId, missionId);
        return ApiResponse.onSuccess(SuccessCode.CREATED);
    }

    @GetMapping("/region/{regionId}")
    public ApiResponse<Page<MissionDto.Response>> getActiveMissionsByRegion(
            @PathVariable Long regionId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ApiResponse.onSuccess(SuccessCode.OK, missionService.getMissionsRegion(regionId, page, size));
    }

    @GetMapping("/region/{regionId}/with-store")
    public ApiResponse<Page<MissionDto.Response>> getActiveMissionsByRegionWithStore(
            @PathVariable Long regionId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ApiResponse.onSuccess(SuccessCode.OK, missionService.getMissionsRegionWithStore(regionId, page, size));
    }

    @GetMapping("/region/{regionId}/available")
    public ApiResponse<Page<MissionDto.Response>> getAvailableMissionsByRegionAndMember(
            @PathVariable Long regionId,
            @RequestParam Long memberId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ApiResponse.onSuccess(SuccessCode.OK, missionService.getMissionsAvailable(regionId, memberId, page, size));
    }

    @GetMapping("/status/{status}")
    public ApiResponse<Page<MissionDto.Response>> getMissionsByStatus(
            @PathVariable String status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ApiResponse.onSuccess(SuccessCode.OK, missionService.getMissionsByStatus(status, page, size));
    }

    @GetMapping("/me/ongoing")
    @Operation(summary = "내가 진행중인 미션 목록 조회 API", description = "내가 진행중인 미션 목록을 10개씩 페이징하여 조회합니다.")
    public ApiResponse<Page<MemberMissionDto.Response>> getMyOngoingMissions(
            @Parameter(hidden = true) @RequestHeader("Member-Id") Long memberId,
            @Parameter(description = "페이지 번호 (1부터 시작)", required = true, example = "1") @PageRange @RequestParam(name = "page") Integer page
    ) {
        Page<MemberMissionDto.Response> missions = missionService.getOngoingMissions(memberId, page, 10);
        return ApiResponse.onSuccess(SuccessCode.OK, missions);
    }

    @PatchMapping("/me/ongoing/{memberMissionId}/complete")
    @Operation(summary = "진행중인 미션 완료 API", description = "진행중인 미션을 완료 상태로 변경하고, 변경된 미션 정보를 반환합니다.")
    public ApiResponse<MemberMissionDto.Response> completeMyMission(
            @Parameter(hidden = true) @RequestHeader("Member-Id") Long memberId,
            @PathVariable Long memberMissionId
    ) {
        MemberMissionDto.Response response = missionService.completeMission(memberId, memberMissionId);
        return ApiResponse.onSuccess(SuccessCode.OK, response);
    }
}
