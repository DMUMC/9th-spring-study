package com.example.umc9th.domain.member.service.command;

import com.example.umc9th.domain.member.dto.req.MemberReqDTO;
import com.example.umc9th.domain.member.dto.res.MemberResDTO;

public interface MemberCommandService {
    // 회원가입
    MemberResDTO.JoinDTO signup(
            MemberReqDTO.JoinDTO dto
    );
}