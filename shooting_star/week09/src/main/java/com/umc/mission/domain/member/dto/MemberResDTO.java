package com.umc.mission.domain.member.dto;

import java.time.LocalDateTime;
import lombok.Builder;

public class MemberResDTO {
    @Builder
    public record JoinDTO(
            Long memberId,
            LocalDateTime createAt
    ){
    }
}
