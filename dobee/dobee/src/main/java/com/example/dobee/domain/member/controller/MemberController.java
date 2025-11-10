package com.example.dobee.domain.member.controller;

import com.example.dobee.domain.member.dto.MemberDto;
import com.example.dobee.domain.member.entity.Member;
import com.example.dobee.domain.member.service.MemberService;
import com.example.dobee.domain.mission.dto.MemberMissionDto;
import com.example.dobee.global.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/{memberId}")
    public ApiResponse<Member> getMemberInfo(@PathVariable Long memberId) {
        return ApiResponse.ok(memberService.getMemberInfo(memberId));
    }

    @GetMapping("/{memberId}/missions/ongoing")
    public ApiResponse<Page<MemberMissionDto.Response>> getOngoingMissions(
            @PathVariable Long memberId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ApiResponse.ok(memberService.getMissionsOngoing(memberId, page, size));
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
