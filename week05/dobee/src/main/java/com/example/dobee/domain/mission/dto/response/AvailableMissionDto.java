package com.example.dobee.domain.mission.dto.response;

import com.example.dobee.domain.mission.entity.Mission;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AvailableMissionDto {
    private Long missionId;
    private String missionTitle;
    private String storeName;
    private String storedAddress;
    private Integer rewardPoints;

    public static AvailableMissionDto from(Mission mission) {
        return AvailableMissionDto.builder()
                .missionId(mission.getMissionId())
                .missionTitle(mission.getTitle())
                .storeName(mission.getStore().getName())
                .storedAddress(mission.getStore().getAddress())
                .rewardPoints(mission.getRewardPoints())
                .build();
    }
}
