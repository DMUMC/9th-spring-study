package com.umc.mission.domain.mission.dto;

import com.umc.mission.domain.member.entity.Member;
import com.umc.mission.domain.mission.entity.MemberMission;
import com.umc.mission.domain.mission.entity.Mission;
import com.umc.mission.domain.mission.enums.MemberMissionStatus;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class MissionDto2 {

    @Getter
    @NoArgsConstructor
    // 도전 요청 dto
    public static class ChallengeRequestDTO{
        public MemberMission toEntity(
                Member member,
                Mission mission
        ){
            // 마감일 계산: 오늘 + 미션의 제한일수
            LocalDateTime deadline = LocalDateTime.now().plusDays(mission.getDeadlineDays());

            return MemberMission.builder()
                    .member(member)
                    .mission(mission)
                    .status(MemberMissionStatus.ONGOING)
                    .deadlineAt(deadline)
                    .build();
        }
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ChallengeResponseDTO{
        private Long memberMissionId;
        private Long memberId;
        private Long missionId;
        private String status;
        private LocalDateTime deadlineAt;

        public static ChallengeResponseDTO from(MemberMission memberMission){
            return ChallengeResponseDTO.builder()
                    .memberMissionId(memberMission.getId())
                    .memberId(memberMission.getMember().getId())
                    .missionId(memberMission.getMission().getId())
                    .status(memberMission.getStatus().name())
                    .deadlineAt(memberMission.getDeadlineAt())
                    .build();
        }
    }
}
