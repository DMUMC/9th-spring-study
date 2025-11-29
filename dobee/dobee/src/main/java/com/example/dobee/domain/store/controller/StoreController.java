package com.example.dobee.domain.store.controller;

import com.example.dobee.domain.mission.dto.MissionDto;
import com.example.dobee.domain.mission.service.MissionService;
import com.example.dobee.domain.store.dto.StoreDto;
import com.example.dobee.domain.store.service.StoreService;
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
@RequiredArgsConstructor
@RequestMapping("/stores")
@Tag(name = "Store", description = "가게 관련 API")
@Validated
public class StoreController {

    private final StoreService storeService;
    private final MissionService missionService;

    @PostMapping
    @Operation(summary = "특정 지역에 가게 추가 API", description = "특정 지역에 새로운 가게를 추가 합니다.")
    public ApiResponse<StoreDto.StoreResponse> addStore(@Valid @RequestBody StoreDto.AddStoreRequest request) {
        StoreDto.StoreResponse response = storeService.addStore(request);

        return ApiResponse.onSuccess(SuccessCode.CREATED, response);
    }

    @GetMapping("/{storeId}/missions")
    @Operation(summary = "특정 가게의 미션 목록 조회 API", description = "특정 가게의 미션 목록을 10개씩 페이징하여 조회합니다.")
    public ApiResponse<Page<MissionDto.Response>> getMissionsByStore(
            @PathVariable Long storeId,
            @Parameter(description = "페이지 번호 (1부터 시작)", required = true, example = "1") @PageRange @RequestParam(name = "page") Integer page
    ) {
        Page<MissionDto.Response> missions = missionService.getMissionsByStore(storeId, page, 10);
        return ApiResponse.onSuccess(SuccessCode.OK, missions);
    }
}
