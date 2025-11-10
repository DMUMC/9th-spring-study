package com.example.dobee.domain.mission.dto;

import com.example.dobee.domain.mission.entity.MemberMission;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class MemberMissionDto {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private Long id;
        private Long memberId;
        private String memberName;
        private Long missionId;
        private String missionTitle;
        private String storeName;
        private String status;
        private LocalDateTime startedAt;
        private LocalDateTime completedAt;
        private LocalDateTime deadlineAt;

        public static Response from(MemberMission memberMission) {
            return Response.builder()
                    .id(memberMission.getId())
                    .memberId(memberMission.getMember().getId())
                    .memberName(memberMission.getMember().getName())
                    .missionId(memberMission.getMission().getId())
                    .missionTitle(memberMission.getMission().getTitle())
                    .storeName(memberMission.getMission().getStore().getName())
                    .status(memberMission.getStatus().name())
                    .startedAt(memberMission.getStartedAt())
                    .completedAt(memberMission.getCompletedAt())
                    .deadlineAt(memberMission.getDeadlineAt())
                    .build();
        }
    }
}
