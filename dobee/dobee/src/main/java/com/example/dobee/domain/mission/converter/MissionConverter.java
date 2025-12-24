package com.example.dobee.domain.mission.converter;

import com.example.dobee.domain.member.entity.Member;
import com.example.dobee.domain.mission.dto.MissionDto;
import com.example.dobee.domain.mission.entity.Mission;
import com.example.dobee.domain.mission.entity.mapping.MemberMission;
import com.example.dobee.domain.mission.enums.MissionStatus;
import com.example.dobee.domain.store.entity.Store;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class MissionConverter {

    public static Mission toMission(MissionDto.AddMissionReqDto request, Store store) {
        return Mission.builder()
                .deadline(request.deadline())
                .conditional(request.conditional())
                .point(request.point())
                .store(store)
                .build();
    }

    public static MissionDto.AddMissionResDto toAddMissionResDto(Mission mission) {
        return new MissionDto.AddMissionResDto(
                mission.getId(),
                mission.getStore().getName(),
                mission.getCreatedAt()
        );
    }

    public static MemberMission toMemberMission(Member member, Mission mission) {
        return MemberMission.builder()
                .member(member)
                .mission(mission)
                .status(MissionStatus.CHALLENGING)
                .build();
    }

    public static MissionDto.ChallengeMissionResDto toChallengeMissionResDto(MemberMission memberMission) {
        return new MissionDto.ChallengeMissionResDto(
                memberMission.getId(),
                memberMission.getStatus().name(),
                memberMission.getCreatedAt()
        );
    }

    public static MissionDto.CompleteMissionResDto toCompleteMissionResDto(MemberMission memberMission) {
        return new MissionDto.CompleteMissionResDto(
                memberMission.getId(),
                memberMission.getStatus().name(),
                memberMission.getUpdatedAt()
        );
    }

    public static MissionDto.MissionPreviewDto toMissionPreviewDto(Mission mission) {
        return MissionDto.MissionPreviewDto.builder()
                .reward(mission.getPoint())
                .missionSpec(mission.getConditional())
                .dDay(mission.getDeadline())
                .build();
    }

    public static MissionDto.MissionPreviewListDto toMissionPreviewListDto(Page<Mission> missionPage) {
        List<MissionDto.MissionPreviewDto> missionList = missionPage.getContent().stream()
                .map(MissionConverter::toMissionPreviewDto)
                .collect(Collectors.toList());

        return MissionDto.MissionPreviewListDto.builder()
                .missionList(missionList)
                .listSize(missionList.size())
                .totalPage(missionPage.getTotalPages())
                .totalElements(missionPage.getTotalElements())
                .isFirst(missionPage.isFirst())
                .isLast(missionPage.isLast())
                .build();
    }

    public static MissionDto.MyChallengingMissionDto toMyChallengingMissionDto(MemberMission memberMission) {
        Mission mission = memberMission.getMission();
        return MissionDto.MyChallengingMissionDto.builder()
                .storeName(mission.getStore().getName())
                .missionSpec(mission.getConditional())
                .dDay(mission.getDeadline())
                .reward(mission.getPoint())
                .build();
    }

    public static MissionDto.MyChallengingMissionListDto toMyChallengingMissionListDto(Page<MemberMission> memberMissionPage) {
        List<MissionDto.MyChallengingMissionDto> missionList = memberMissionPage.getContent().stream()
                .map(MissionConverter::toMyChallengingMissionDto)
                .collect(Collectors.toList());

        return MissionDto.MyChallengingMissionListDto.builder()
                .missionList(missionList)
                .listSize(missionList.size())
                .totalPage(memberMissionPage.getTotalPages())
                .totalElements(memberMissionPage.getTotalElements())
                .isFirst(memberMissionPage.isFirst())
                .isLast(memberMissionPage.isLast())
                .build();
    }
}