package com.example.umc9th.domain.member.converter;

import com.example.umc9th.domain.member.dto.res.MemberResDTO;
import com.example.umc9th.domain.member.entity.Member;

public class MemberConverter {

    public static MemberResDTO.WriterDTO toWriterDTO(Member member) {
        return MemberResDTO.WriterDTO.builder()
                .memberId(member.getId())
                .nickname(member.getNickname())
                .build();
    }
}