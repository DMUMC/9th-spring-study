package com.example.dobee.domain.member.dto.response;

import com.example.dobee.domain.mission.entity.Mission;
import com.example.dobee.domain.mission.entity.UserMission;
import com.example.dobee.domain.mission.entity.UserMissionStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MyMissionDto {
    private Long userMissionId;
    private String missionTitle;
    private String storeName;
    private UserMissionStatus status;
    private Integer rewardPoints;

    public static MyMissionDto from(UserMission userMission) {
        return MyMissionDto.builder()
                .userMissionId(userMission.getUserMissionId())
                .missionTitle(userMission.getMission().getTitle())
                .storeName(userMission.getMission().getStore().getName())
                .status(userMission.getStatus())
                .rewardPoints(userMission.getMission().getRewardPoints())
                .build();
    }
}
