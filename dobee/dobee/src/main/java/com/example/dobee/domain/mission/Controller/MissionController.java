package com.example.dobee.domain.mission.Controller;

import com.example.dobee.domain.mission.dto.MissionDto;
import com.example.dobee.domain.mission.service.MissionService;
import com.example.dobee.global.code.SuccessCode;
import com.example.dobee.global.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/missions")
@RequiredArgsConstructor
public class MissionController {

    private final MissionService missionService;

    @PostMapping("/challenges/without-lock")
    public ApiResponse<Void> challengeMissionWithoutLock(
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
}
