package com.umc.mission.domain.member.service.command;

import com.umc.mission.domain.member.dto.MemberReqDTO;
import com.umc.mission.domain.member.dto.MemberResDTO;

public interface MemberCommandService {
    MemberResDTO.JoinDTO signUp(
            MemberReqDTO.JoinDTO dto
    );
}
