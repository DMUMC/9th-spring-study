package com.example.dobee.domain.member.dto;

import com.example.dobee.domain.member.enums.Gender;
import com.example.dobee.domain.store.enums.Address;
import com.example.dobee.global.annotation.ExistFoods;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class MemberDto {

    /**
     * 회원가입 요청 DTO
     * @param name 이름
     * @param nickname 닉네임
     * @param gender 성별
     * @param birth 생년월일
     * @param address 주소
     * @param detailAddress 상세주소
     * @param email 이메일
     * @param phoneNumber 전화번호
     * @param agreedTerms 동의한 약관 ID 목록
     * @param preferredFoods 선호하는 음식 ID 목록
     */
    public record MemberJoinReqDto(
            @Email
            String email,
            @NotBlank
            String password,
            @NotBlank
            String name,
            @NotBlank
            String nickname,
            @NotNull
            Gender gender,
            @NotNull
            LocalDate birth,
            @NotNull
            Address address,
            @NotNull
            String detailAddress,
            @NotBlank
            String phoneNumber,
            List<Long> agreedTerms,
            @ExistFoods
            List<Long> preferredFoods
    ) {}

    /**
     * 회원가입 응답 DTO
     * @param memberId 생성된 회원 ID
     * @param nickname 생성된 회원 닉네임
     * @param createdAt 회원가입 일시
     */
    @Builder
    public record MemberJoinResDto(
            Long memberId,
            String nickname,
            LocalDateTime createdAt
    ) {}

    public record LoginReqDto(
            @NotBlank
            String email,
            @NotBlank
            String password
    ){}

    @Builder
    public record LoginResDto(
            Long memberId,
            String accessToken
    ){}
}
