package com.example.dobee.domain.mission.service;

import com.example.dobee.domain.member.entity.Member;
import com.example.dobee.domain.member.exception.MemberNotFoundException;
import com.example.dobee.domain.member.repository.MemberRepository;
import com.example.dobee.domain.mission.dto.MissionDto;
import com.example.dobee.domain.mission.entity.MemberMission;
import com.example.dobee.domain.mission.entity.Mission;
import com.example.dobee.domain.mission.enums.MemberMissionStatus;
import com.example.dobee.domain.mission.exception.AlreadyChallengedException;
import com.example.dobee.domain.mission.exception.MissionNotFoundException;
import com.example.dobee.domain.mission.repository.MemberMissionRepository;
import com.example.dobee.domain.mission.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    @Transactional
    public void challengeMissionWithoutLock(Long memberId, Long missionId) {
        log.info("Challenge mission without lock - memberId: {}, missionId: {}", memberId, missionId);

        Optional<MemberMission> existing = memberMissionRepository.findByMemberIdAndMissionId(memberId, missionId);
        if (existing.isPresent()) throw new AlreadyChallengedException("Already challenged this mission");

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException("Member not found"));
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new MissionNotFoundException("Mission not found"));

        MemberMission memberMission = MemberMission.builder()
                .member(member)
                .mission(mission)
                .status(MemberMissionStatus.ONGOING)
                .startedAt(LocalDateTime.now())
                .deadlineAt(LocalDateTime.now().plusDays(7))
                .build();

        memberMissionRepository.save(memberMission);
    }

    @Transactional
    public void challengeMissionWithLock(Long memberId, Long missionId) {
        log.info("Challenge mission with lock - memberId: {}, missionId: {}", memberId, missionId);

        Optional<MemberMission> existing = memberMissionRepository.findByMemberIdAndMissionIdWithLock(memberId, missionId);
        if (existing.isPresent()) throw new AlreadyChallengedException("Already challenged this mission");

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException("Member not found"));
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new MissionNotFoundException("Mission not found"));

        MemberMission memberMission = MemberMission.builder()
                .member(member)
                .mission(mission)
                .status(MemberMissionStatus.ONGOING)
                .startedAt(LocalDateTime.now())
                .deadlineAt(LocalDateTime.now().plusDays(7))
                .build();

        memberMissionRepository.save(memberMission);
    }

    @Transactional(readOnly = true)
    public Page<MissionDto.Response> getMissionsRegion(Long regionId, int page, int size) {
        log.info("Getting missions by region - regionId: {}", regionId);
        return missionRepository.findActiveByRegionId(regionId, PageRequest.of(page, size))
                .map(MissionDto.Response::from);
    }

    @Transactional(readOnly = true)
    public Page<MissionDto.Response> getMissionsRegionWithStore(Long regionId, int page, int size) {
        log.info("Getting missions by region with store - regionId: {}", regionId);
        return missionRepository.findActiveByRegionIdWithStore(regionId, PageRequest.of(page, size))
                .map(MissionDto.Response::from);
    }

    @Transactional(readOnly = true)
    public Page<MissionDto.Response> getMissionsAvailable(Long regionId, Long memberId, int page, int size) {
        log.info("Getting available missions - regionId: {}, memberId: {}", regionId, memberId);
        return missionRepository.findAvailableMissionsByRegionAndMember(regionId, memberId, PageRequest.of(page, size))
                .map(MissionDto.Response::from);
    }

    @Transactional(readOnly = true)
    public Page<MissionDto.Response> getMissionsByStatus(String status, int page, int size) {
        log.info("Getting missions by status - status: {}", status);
        return missionRepository.findByStatus(status, PageRequest.of(page, size))
                .map(MissionDto.Response::from);
    }
}
