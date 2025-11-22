package com.example.umc9th.domain.member.dto.req;

import com.example.umc9th.domain.member.enums.Gender;
import com.example.umc9th.global.annotation.ExistFoods;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public class MemberReqDTO {

    public record JoinDTO(
            @NotBlank
            String name,
            @NotNull
            Gender gender,
            @NotNull
            LocalDate birth,
            @NotBlank
            String address,
            String specAddress,
            @NotBlank
            String nickname,
            @NotBlank
            String email,
            @NotBlank
            String phoneNumber,
            @ExistFoods
            List<Long> preferCategory
    ){}
}