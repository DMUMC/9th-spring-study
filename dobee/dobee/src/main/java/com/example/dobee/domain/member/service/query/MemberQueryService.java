package com.example.dobee.domain.member.service.query;

import com.example.dobee.domain.member.dto.MemberDto;
import jakarta.validation.Valid;

public interface MemberQueryService {
    MemberDto.LoginResDto login(MemberDto.@Valid LoginReqDto dto);
}
