package com.example.dobee.domain.mission.controller;

import com.example.dobee.domain.mission.dto.MissionDto;
import com.example.dobee.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "Mission", description = "미션 관련 API")
public interface MissionControllerDocs {

    @Operation(summary = "가게에 미션 추가 API", description = "특정 가게에 새로운 미션을 추가합니다.")
    ApiResponse<MissionDto.AddMissionResDto> addMission(@RequestBody @Valid MissionDto.AddMissionReqDto request);

    @Operation(summary = "미션 도전하기 API", description = "사용자가 특정 미션에 도전합니다.")
    ApiResponse<MissionDto.ChallengeMissionResDto> challengeMission(@RequestBody @Valid MissionDto.ChallengeMissionReqDto request);

    @Operation(summary = "특정 가게의 미션 목록 조회 API", description = "특정 가게에 등록된 미션 목록을 페이징하여 조회합니다.")
    @Parameters({
            @Parameter(name = "storeId", description = "가게 ID", required = true),
            @Parameter(name = "page", description = "페이지 번호 (1부터 시작)", required = true)
    })
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "MISSION_003", description = "가게의 미션 목록 조회가 완료되었습니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    })
    ApiResponse<MissionDto.MissionPreviewListDto> getMissionList(@PathVariable Long storeId, @RequestParam Integer page);

    @Operation(summary = "내가 진행중인 미션 목록 조회 API", description = "내가 도전 중인 미션 목록을 페이징하여 조회합니다.")
    @Parameters({
            @Parameter(name = "memberId", description = "회원 ID", required = true),
            @Parameter(name = "page", description = "페이지 번호 (1부터 시작)", required = true)
    })
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "MISSION_004", description = "진행중인 미션 목록 조회가 완료되었습니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    })
    ApiResponse<MissionDto.MyChallengingMissionListDto> getMyChallengingMissions(@RequestParam Long memberId, @RequestParam Integer page);

    @Operation(summary = "진행중인 미션 완료 API", description = "사용자가 진행중인 미션을 완료 상태로 변경합니다.")
    @Parameter(name = "memberMissionId", description = "도전 중인 미션의 ID (MemberMission ID)", required = true)
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "MISSION_005", description = "미션 완료 처리가 되었습니다.", content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    })
    ApiResponse<MissionDto.CompleteMissionResDto> completeMission(@PathVariable Long memberMissionId);
}