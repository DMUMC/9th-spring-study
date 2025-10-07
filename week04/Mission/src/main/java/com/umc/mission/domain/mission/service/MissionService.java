package com.umc.mission.domain.mission.service;

import com.umc.mission.domain.member.entity.Member;
import com.umc.mission.domain.member.repository.MemberRepository;
import com.umc.mission.domain.mission.entity.MemberMission;
import com.umc.mission.domain.mission.entity.Mission;
import com.umc.mission.domain.mission.repository.MemberMissionRepository;
import com.umc.mission.domain.mission.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MissionService {

    private final MemberMissionRepository memberMissionRepository;
    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;

    // 락 없이 미션 도전 신청 (동시성 문제 발생 가능)
    @Transactional
    public MemberMission challengeMissionWithoutLock(Long memberId, Long missionId) {
        log.info("Challenge mission without lock - memberId: {}, missionId: {}", memberId, missionId);

        Optional<MemberMission> existing = memberMissionRepository
                .findByMemberIdAndMissionId(memberId, missionId);

        if (existing.isPresent()) {
            throw new IllegalStateException("Already challenged this mission");
        }

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));

        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new IllegalArgumentException("Mission not found"));

        MemberMission memberMission = MemberMission.builder()
                .member(member)
                .mission(mission)
                .status("ongoing")
                .startedAt(LocalDateTime.now())
                .deadlineAt(LocalDateTime.now().plusDays(7))
                .build();

        return memberMissionRepository.save(memberMission);
    }

    // Pessimistic Write Lock으로 동시성 문제 해결 (중복 방지)
    @Transactional
    public MemberMission challengeMissionWithLock(Long memberId, Long missionId) {
        log.info("Challenge mission with lock - memberId: {}, missionId: {}", memberId, missionId);

        // Pessimistic Write Lock으로 중복 체크
        Optional<MemberMission> existing = memberMissionRepository
                .findByMemberIdAndMissionIdWithLock(memberId, missionId);

        if (existing.isPresent()) {
            throw new IllegalStateException("Already challenged this mission");
        }

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));

        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new IllegalArgumentException("Mission not found"));

        MemberMission memberMission = MemberMission.builder()
                .member(member)
                .mission(mission)
                .status("ongoing")
                .startedAt(LocalDateTime.now())
                .deadlineAt(LocalDateTime.now().plusDays(7))
                .build();

        return memberMissionRepository.save(memberMission);
    }
}