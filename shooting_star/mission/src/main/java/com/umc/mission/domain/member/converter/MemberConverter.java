package com.umc.mission.domain.member.converter;

import com.umc.mission.domain.member.dto.MemberReqDTO;
import com.umc.mission.domain.member.dto.MemberResDTO;
import com.umc.mission.domain.member.entity.Member;
import com.umc.mission.global.auth.enums.Role;

public class MemberConverter {
    // entity -> MemberResDTO
    public static MemberResDTO.JoinDTO toJoinDTO(Member member){
        return MemberResDTO.JoinDTO.builder()
                .memberId(member.getId())
                .createAt(member.getCreatedAt())
                .build();
    }

    // dto -> entity
    public static Member toMember(
            MemberReqDTO.JoinDTO dto,
            String password,
            Role role
    ){
        return Member.builder()
                .name(dto.name())
                .password(password)
                .role(role)
                .email(dto.email())
                .nickname(dto.nickname())
                .phoneNum(dto.phoneNum())
                .profileImage(dto.profileImage())
                .gender(dto.gender())
                .socialType(dto.socialType())
                .build();
    }

    public static MemberResDTO.LoginDTO toLoginDTO(Member member, String accessToken){
        return MemberResDTO.LoginDTO.builder()
                .memberId(member.getId())
                .accessToken(accessToken)
                .build();
    }
}
