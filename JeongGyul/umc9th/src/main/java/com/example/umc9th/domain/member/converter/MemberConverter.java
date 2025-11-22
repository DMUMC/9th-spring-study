package com.example.umc9th.domain.member.converter;

import com.example.umc9th.domain.member.dto.req.MemberReqDTO;
import com.example.umc9th.domain.member.dto.res.MemberResDTO;
import com.example.umc9th.domain.member.entity.Member;

public class MemberConverter {

    // 내가 쓴 리뷰 조회 시 닉네임 담아서 전달
    public static MemberResDTO.WriterDTO toWriterDTO(Member member) {
        return MemberResDTO.WriterDTO.builder()
                .memberId(member.getId())
                .nickname(member.getNickname())
                .build();
    }

    // Entity -> DTO (회원가입)
    public static MemberResDTO.JoinDTO toJoinDTO(
            Member member
    ) {
        return MemberResDTO.JoinDTO.builder()
                .memberId(member.getId())
                .createdAt(member.getCreatedAt())
                .build();
    }

    // DTO -> Entity (회원가입)
    public static Member toMember(
            MemberReqDTO.JoinDTO dto
    ) {
        return Member.builder()
                .name(dto.name())
                .birthday(dto.birth())
                .address(dto.address())
                .detailAddress(dto.specAddress())
                .gender(dto.gender())
                .email(dto.email())
                .phoneNumber(dto.phoneNumber())
                .nickname(dto.nickname())
                .build();
    }
}