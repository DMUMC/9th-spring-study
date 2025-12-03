package com.example.dobee.domain.member.controller;

import com.example.dobee.domain.member.dto.MemberDto;
import com.example.dobee.domain.member.exception.code.MemberSuccessCode;
import com.example.dobee.domain.member.service.command.MemberCommandService;
import com.example.dobee.domain.member.service.query.MemberQueryService;
import com.example.dobee.global.apiPayload.ApiResponse;
import com.example.dobee.global.auth.enums.Role;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberCommandService memberCommandService;
    private final MemberQueryService memberQueryService;


    @PostMapping("/sign-up")
    public ApiResponse<MemberDto.MemberJoinResDto> signUp(
            @RequestBody @Valid MemberDto.MemberJoinReqDto dto
    ) {
        return ApiResponse.onSuccess(MemberSuccessCode.MEMBER_JOIN_SUCCESS, memberCommandService.signup(dto));
    }

    @PostMapping("/login")
    public ApiResponse<MemberDto.LoginResDto> login(
            @RequestBody @Valid MemberDto.LoginReqDto dto
    ){
        return ApiResponse.onSuccess(MemberSuccessCode.MEMBER_LOGIN_SUCCESS, memberQueryService.login(dto));
    }
}
