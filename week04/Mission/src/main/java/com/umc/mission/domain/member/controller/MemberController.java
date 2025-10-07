package com.umc.mission.domain.member.controller;

import com.umc.mission.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // 회원 탈퇴 (Soft Delete)
    @DeleteMapping("/{memberId}/withdraw")
    public ResponseEntity<String> withdrawMember(@PathVariable Long memberId) {
        memberService.withdrawMember(memberId);
        return ResponseEntity.ok("Member withdrawn successfully");
    }

    // 회원 완전 삭제 (Hard Delete)
    @DeleteMapping("/{memberId}/permanent")
    public ResponseEntity<String> deleteMemberPermanently(@PathVariable Long memberId) {
        memberService.deleteMemberPermanently(memberId);
        return ResponseEntity.ok("Member permanently deleted");
    }
}