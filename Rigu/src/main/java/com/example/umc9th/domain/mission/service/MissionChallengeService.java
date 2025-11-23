package com.example.umc9th.domain.mission.service;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.domain.mission.dto.MissionChallengeRequest;
import com.example.umc9th.domain.mission.dto.MissionChallengeResponse;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.entity.UserMission;
import com.example.umc9th.domain.mission.repository.MissionRepository;
import com.example.umc9th.domain.mission.repository.UserMissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MissionChallengeService {

    private final UserMissionRepository userMissionRepository;
    private final MissionRepository missionRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public MissionChallengeResponse addChallenge(Long storeId, MissionChallengeRequest request) {

        Member member = memberRepository.findById(request.userId())
                .orElseThrow(() -> new RuntimeException("MEMBER_NOT_FOUND"));

        Mission mission = missionRepository.findById(request.missionId())
                .orElseThrow(() -> new RuntimeException("MISSION_NOT_FOUND"));

        UserMission userMission = UserMission.builder()
                .member(member)
                .mission(mission)
                .status("IN_PROGRESS")
                .build();

        UserMission saved = userMissionRepository.save(userMission);

        return new MissionChallengeResponse(
                saved.getId(),
                saved.getStatus(),
                saved.getCreatedAt()
        );
    }
}
