package com.umc.mission.domain.member.service.query;

import com.umc.mission.domain.member.dto.MemberReqDTO;
import com.umc.mission.domain.member.dto.MemberResDTO.LoginDTO;
import jakarta.validation.Valid;

public interface MemberQueryService {
    LoginDTO login(MemberReqDTO.@Valid LoginDTO dto);
}
