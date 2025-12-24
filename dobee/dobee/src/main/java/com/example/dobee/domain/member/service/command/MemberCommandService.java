package com.example.dobee.domain.member.service.command;

import com.example.dobee.domain.member.dto.MemberDto;
import com.example.dobee.global.auth.enums.Role;

public interface MemberCommandService {

    MemberDto.MemberJoinResDto signup(
            MemberDto.MemberJoinReqDto dto
    );

}
