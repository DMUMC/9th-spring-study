package com.umc.controller;

import com.umc.annotation.ValidPage;
import com.umc.apiController.ApiResponse;
import com.umc.apiController.code.GeneralResponseCode;
import com.umc.dto.request.MemberMissionRequestDto;
import com.umc.dto.response.MemberMissionResponseDTO;
import com.umc.dto.response.SimpleMemberMissionResponseDto;
import com.umc.exception.InvalidPageException;
import com.umc.service.MemberMissionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/member-missions")
@RequiredArgsConstructor
@Validated
@Tag(name = "미션 도전 API", description = "회원 미션 도전 관련 API")
public class MemberMissionController {
    
    private final MemberMissionService memberMissionService;
    
    @PostMapping
    @Operation(summary = "미션 도전하기", description = "가게의 미션을 도전 중인 미션에 추가합니다. (하드코딩된 회원 ID: 1)")
    public ApiResponse<SimpleMemberMissionResponseDto> challengeMission(
            @Valid @RequestBody MemberMissionRequestDto requestDto
    ) {
        SimpleMemberMissionResponseDto response = memberMissionService.challengeMission(requestDto);
        return ApiResponse.onSuccess(response);
    }
    
    @GetMapping("/challenging")
    @Operation(summary = "도전 중인 미션 목록 조회", description = "내가 현재 도전 중인 미션 목록을 조회합니다. (하드코딩된 회원 ID: 1)")
    public ApiResponse<List<SimpleMemberMissionResponseDto>> getMyChallengingMissions() {
        List<SimpleMemberMissionResponseDto> missions = memberMissionService.getMyChallengingMissions();
        return ApiResponse.onSuccess(missions);
    }
    
    @GetMapping("/completed")
    @Operation(summary = "완료한 미션 목록 조회", description = "내가 완료한 미션 목록을 조회합니다. (하드코딩된 회원 ID: 1)")
    public ApiResponse<List<SimpleMemberMissionResponseDto>> getMyCompletedMissions() {
        List<SimpleMemberMissionResponseDto> missions = memberMissionService.getMyCompletedMissions();
        return ApiResponse.onSuccess(missions);
    }
    
    @PatchMapping("/{memberMissionId}/complete")
    @Operation(summary = "미션 완료하기", description = "도전 중인 미션을 완료 상태로 변경합니다. (하드코딩된 회원 ID: 1)")
    public ApiResponse<SimpleMemberMissionResponseDto> completeMission(
            @Parameter(description = "회원 미션 ID", example = "1")
            @PathVariable Long memberMissionId
    ) {
        SimpleMemberMissionResponseDto response = memberMissionService.completeMission(memberMissionId);
        return ApiResponse.onSuccess(response);
    }
    
    @GetMapping("/members/{memberId}/challenging")
    @Operation(summary = "내가 진행중인 미션 목록 (페이징)", description = "특정 회원이 진행중인 미션 목록을 페이징하여 조회합니다. 한 페이지에 10개씩 조회됩니다.")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "조회 성공",
                    content = @Content(schema = @Schema(implementation = MemberMissionResponseDTO.MemberMissionPreviewListDTO.class))
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "페이지 번호가 1 미만인 경우",
                    content = @Content(schema = @Schema(implementation = ApiResponse.class))
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404",
                    description = "회원을 찾을 수 없는 경우",
                    content = @Content(schema = @Schema(implementation = ApiResponse.class))
            )
    })
    public ApiResponse<MemberMissionResponseDTO.MemberMissionPreviewListDTO> getMyChallengingMissionsPaged(
            @Parameter(description = "회원 ID", example = "1")
            @PathVariable Long memberId,
            @Parameter(description = "페이지 번호 (1 이상)", example = "1")
            @RequestParam @ValidPage Integer page
    ) {
        if (page == null || page < 1) {
            throw new InvalidPageException(GeneralResponseCode.BAD_REQUEST);
        }
        MemberMissionResponseDTO.MemberMissionPreviewListDTO missions = 
                memberMissionService.getMyChallengingMissionsPaged(memberId, page);
        return ApiResponse.onSuccess(missions);
    }
}
