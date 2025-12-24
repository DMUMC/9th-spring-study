package com.example.dobee.domain.member.converter;

import com.example.dobee.domain.member.dto.MemberDto;
import com.example.dobee.domain.member.entity.Member;
import com.example.dobee.global.auth.enums.Role;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

public class MemberConverter {

    public static MemberDto.MemberJoinResDto toJoinDto(Member member){
        return MemberDto.MemberJoinResDto.builder()
                .memberId(member.getId())
                .createdAt(member.getCreatedAt())
                .build();
    }

    public static Member toMember(
            MemberDto.MemberJoinReqDto dto,
            String password,
            Role role
    ) {
        return Member.builder()
                .email(dto.email())
                .password(password)
                .role(role)
                .name(dto.name())
                .nickname(dto.nickname())
                .birth(dto.birth())
                .address(dto.address())
                .detailAddress(dto.detailAddress())
                .gender(dto.gender())
                .build();
    }

    public static MemberDto.LoginResDto toLoginDto(Member member, String accessToken) {
        return MemberDto.LoginResDto.builder()
                .memberId(member.getId())
                .accessToken(accessToken)
                .build();
    }
}
