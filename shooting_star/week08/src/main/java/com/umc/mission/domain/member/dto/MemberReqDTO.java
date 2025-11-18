package com.umc.mission.domain.member.dto;

import com.umc.mission.domain.member.entity.MemberPreferredFood;
import com.umc.mission.domain.member.enums.FoodType;
import java.util.List;

public class MemberReqDTO {
    public record JoinDTO(
            String name,
            String email,
            String nickname,
            String phoneNum,
            String gender,
            String profileImage,
            String socialType,
            List<FoodType> preferCategory
    ){
    }
}
