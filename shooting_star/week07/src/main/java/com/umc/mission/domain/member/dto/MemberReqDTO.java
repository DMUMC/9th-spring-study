package com.umc.mission.domain.member.dto;

public class MemberReqDTO {
    public record JoinDTO(
            String name,
            String email,
            String nickname,
            String phoneNum,
            String gender,
            String profileImage
    ){

    }
}
