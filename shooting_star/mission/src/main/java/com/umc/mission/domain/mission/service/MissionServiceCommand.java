package com.umc.mission.domain.mission.service;

import com.umc.mission.domain.mission.dto.MissionDto2;
import com.umc.mission.domain.mission.entity.MemberMission;
import org.springframework.data.domain.Page;

public interface MissionServiceCommand {
    MemberMission challengeMission(Long memberId, Long missionId, MissionDto2.ChallengeRequestDTO request);
    Page<MemberMission> completeMission(Long memberMissionId, Integer page);
}
