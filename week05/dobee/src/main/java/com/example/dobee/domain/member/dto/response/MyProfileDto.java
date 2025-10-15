package com.example.dobee.domain.member.dto.response;

import com.example.dobee.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MyProfileDto {

    private String nickname;
    private String email;
    private String phoneNumber;
    private Boolean isPhoneVerified;
    private Integer points;

    public static MyProfileDto from(Member member) {
        return MyProfileDto.builder()
                .nickname(member.getNickname())
                .email(member.getEmail())
                .phoneNumber(member.getPhoneNumber())
                .isPhoneVerified(member.getPhoneVerified())
                .points(member.getPoints())
                .build();
    }
}
