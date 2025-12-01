// com.example.umc9th.domain.mission.converter.UserMissionConverter

package com.example.umc9th.domain.mission.converter;

import com.example.umc9th.domain.mission.dto.MyMissionPageResponse;
import com.example.umc9th.domain.mission.dto.MyMissionResponse;
import com.example.umc9th.domain.mission.entity.UserMission;
import org.springframework.data.domain.Page;

public class UserMissionConverter {

    public static MyMissionPageResponse toMyMissionPageResponse(Page<UserMission> page) {
        return MyMissionPageResponse.builder()
                .missions(
                        page.getContent().stream()
                                .map(UserMissionConverter::toMyMissionResponse)
                                .toList()
                )
                .page(page.getNumber() + 1)
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .first(page.isFirst())
                .last(page.isLast())
                .build();
    }

    public static MyMissionResponse toMyMissionResponse(UserMission userMission) {
        var mission = userMission.getMission();

        return MyMissionResponse.builder()
                .userMissionId(userMission.getId())
                .missionId(mission.getId())
                .storeName(mission.getStore().getName())
                .content(mission.getContent())
                .point(mission.getPoint())
                .deadline(mission.getDeadline())
                .build();
    }
}
