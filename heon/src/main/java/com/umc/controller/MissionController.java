package com.umc.controller;

import com.umc.annotation.ValidPage;
import com.umc.apiController.ApiResponse;
import com.umc.apiController.code.GeneralResponseCode;
import com.umc.dto.response.MissionResponseDTO;
import com.umc.exception.InvalidPageException;
import com.umc.service.MissionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/missions")
@RequiredArgsConstructor
@Validated
@Tag(name = "미션 API", description = "가게 미션 관련 API")
public class MissionController {
    
    private final MissionService missionService;
    
    @GetMapping("/stores/{storeId}")
    @Operation(summary = "특정 가게의 미션 목록 (페이징)", description = "특정 가게의 미션 목록을 페이징하여 조회합니다. 한 페이지에 10개씩 조회됩니다.")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "조회 성공",
                    content = @Content(schema = @Schema(implementation = MissionResponseDTO.MissionPreviewListDTO.class))
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "페이지 번호가 1 미만인 경우",
                    content = @Content(schema = @Schema(implementation = ApiResponse.class))
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404",
                    description = "가게를 찾을 수 없는 경우",
                    content = @Content(schema = @Schema(implementation = ApiResponse.class))
            )
    })
    public ApiResponse<MissionResponseDTO.MissionPreviewListDTO> getStoreMissions(
            @Parameter(description = "가게 ID", example = "1")
            @PathVariable Long storeId,
            @Parameter(description = "페이지 번호 (1 이상)", example = "1")
            @RequestParam @ValidPage Integer page
    ) {
        if (page == null || page < 1) {
            throw new InvalidPageException(GeneralResponseCode.BAD_REQUEST);
        }
        MissionResponseDTO.MissionPreviewListDTO missions = missionService.getStoreMissionsPaged(storeId, page);
        return ApiResponse.onSuccess(missions);
    }
}
