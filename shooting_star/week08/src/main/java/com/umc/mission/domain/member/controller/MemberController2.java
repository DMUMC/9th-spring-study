package com.umc.mission.domain.member.controller;

import com.umc.mission.domain.member.dto.MemberReqDTO;
import com.umc.mission.domain.member.dto.MemberResDTO.JoinDTO;
import com.umc.mission.domain.member.exception.code.MemberSuccessCode;
import com.umc.mission.domain.member.service.command.MemberCommandService;
import com.umc.mission.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController2 {
    private final MemberCommandService memberCommandService;

    // 회원가입
    @PostMapping("/sign-up")
    public ApiResponse<JoinDTO> signUp(
            @RequestBody MemberReqDTO.JoinDTO dto
    ){
        return ApiResponse.onSuccess(MemberSuccessCode.FOUND, memberCommandService.signUp(dto));
    }
}
