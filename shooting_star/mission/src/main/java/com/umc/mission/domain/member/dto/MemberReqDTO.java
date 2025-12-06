package com.umc.mission.domain.member.dto;

import com.umc.mission.domain.member.entity.MemberPreferredFood;
import com.umc.mission.domain.member.enums.FoodType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public class MemberReqDTO {
    public record JoinDTO(
            @NotBlank
            String name,
            @Email
            String email,
            @NotBlank
            String password,
            String nickname,
            @NotNull
            String phoneNum,
            @NotNull
            String gender,
            String profileImage,
            String socialType,
            List<FoodType> preferCategory
    ) {
    }

    // 로그인 (요청시 dto)
    public record LoginDTO(
        @NotBlank
        String email,
        @NotBlank
        String password
    ) {
    }
}
