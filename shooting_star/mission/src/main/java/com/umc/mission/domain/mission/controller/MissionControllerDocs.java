package com.umc.mission.domain.mission.controller;

import com.umc.mission.domain.mission.dto.MemberMissionResDTO;
import com.umc.mission.domain.mission.dto.MissionResDTO;
import com.umc.mission.global.annotation.CheckPage;
import com.umc.mission.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.PathVariable;

public interface MissionControllerDocs {
    @Operation(
            summary = "가게의 미션 목록 조회",
            description = "특정 가게의 미션을 모두 조회합니다. 페이지네이션으로 제공합니다."
    )
    @Parameters({
            @Parameter(name = "storeId", description = "가게의 id입니다."),
            @Parameter(name = "page", description = "페이지 번호입니다. 1부터 시작합니다.")
    })
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    ApiResponse<MissionResDTO.MissionPreviewListDTO> getMissionListByStore(
            Long storeId,
            Integer page
    );

    @Operation(
            summary = "내가 진행중인 미션 목록을 조회",
            description = "특정 회원이 진행중인 미션들의 목록을 조회합니다. 페이지네이션으로 제공합니다."
    )
    @Parameters({
            @Parameter(name = "memberId", description = "회원의 id입니다."),
            @Parameter(name = "page", description = "페이지 번호입니다. 1부터 시작합니다.")
    })
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    ApiResponse<MemberMissionResDTO.ChallengingMissionPreviewListDTO> getMyChallengingMissions(
            Long memberId,
            Integer page
    );
}
