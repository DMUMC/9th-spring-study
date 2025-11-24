package com.umc.mission.domain.member.controller;

import com.umc.mission.domain.member.dto.MemberDto;
import com.umc.mission.domain.member.entity.Member;
import com.umc.mission.domain.member.service.MemberService;
import com.umc.mission.domain.mission.dto.MemberMissionDto;
import com.umc.mission.global.response.ApiResponse;
import com.umc.mission.global.validation.annotation.CheckPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
@Validated
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/{memberId}")
    public ApiResponse<Member> getMemberInfo(@PathVariable Long memberId) {
        return ApiResponse.ok(memberService.getMemberInfo(memberId));
    }

    @GetMapping("/{memberId}/missions/ongoing")
    @Operation(summary = "내가 진행 중인 미션 목록 조회 API", description = "특정 회원이 현재 진행 중인 미션 목록을 조회합니다.")
    @Parameters({
            @Parameter(name = "memberId", description = "회원의 ID"),
            @Parameter(name = "page", description = "페이지 번호 (1부터 시작)")
    })
    public ApiResponse<MemberMissionDto.PageResponse> getOngoingMissions(
            @PathVariable Long memberId,
            @CheckPage @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<MemberMissionDto.Response> missionPage = memberService.getMissionsOngoing(memberId, page - 1, size);
        return ApiResponse.ok(MemberMissionDto.PageResponse.from(missionPage));
    }

    @GetMapping("/{memberId}/missions/completed")
    public ApiResponse<Page<MemberMissionDto.Response>> getCompletedMissions(
            @PathVariable Long memberId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ApiResponse.ok(memberService.getMissionsCompleted(memberId, page, size));
    }

    @GetMapping("/{memberId}/missions/stats")
    public ApiResponse<MemberDto.MissionStats> getMissionStats(@PathVariable Long memberId) {
        return ApiResponse.ok(memberService.getMissionStats(memberId));
    }

    @DeleteMapping("/{memberId}/withdraw")
    public ApiResponse<Void> withdrawMember(@PathVariable Long memberId) {
        memberService.withdrawMember(memberId);
        return ApiResponse.ok();
    }

    @DeleteMapping("/{memberId}/permanent")
    public ApiResponse<Void> deleteMemberPermanently(@PathVariable Long memberId) {
        memberService.deleteMemberPermanently(memberId);
        return ApiResponse.ok();
    }

}