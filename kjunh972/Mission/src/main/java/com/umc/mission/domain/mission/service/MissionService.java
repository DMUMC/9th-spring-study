package com.umc.mission.domain.mission.service;

import com.umc.mission.domain.member.entity.Member;
import com.umc.mission.domain.member.exception.MemberNotFoundException;
import com.umc.mission.domain.member.repository.MemberRepository;
import com.umc.mission.domain.mission.dto.MemberMissionDto;
import com.umc.mission.domain.mission.dto.MissionDto;
import com.umc.mission.domain.mission.entity.MemberMission;
import com.umc.mission.domain.mission.entity.Mission;
import com.umc.mission.domain.mission.enums.MemberMissionStatus;
import com.umc.mission.domain.mission.exception.AlreadyChallengedException;
import com.umc.mission.domain.mission.exception.MissionNotFoundException;
import com.umc.mission.domain.mission.repository.MemberMissionRepository;
import com.umc.mission.domain.mission.repository.MissionRepository;
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

    // 특정 가게의 미션 목록 조회
    @Transactional(readOnly = true)
    public MissionDto.PageResponse getMissionsByStore(Long storeId, int page) {
        log.info("Getting missions by store - storeId: {}, page: {}", storeId, page);
        int size = 10;
        Page<Mission> missionPage = missionRepository.findByStoreIdAndStatus(storeId, "ACTIVE", PageRequest.of(page - 1, size));
        return MissionDto.PageResponse.from(missionPage);
    }

    // 내가 진행중인 미션 목록 조회
    @Transactional(readOnly = true)
    public MemberMissionDto.PageResponse getMyOngoingMissions(Long memberId, int page) {
        log.info("Getting my ongoing missions - memberId: {}, page: {}", memberId, page);
        int size = 10;
        Page<MemberMission> memberMissionPage = memberMissionRepository.findByMemberIdAndStatus(
                memberId, MemberMissionStatus.ONGOING, PageRequest.of(page - 1, size)
        );
        return MemberMissionDto.PageResponse.from(memberMissionPage);
    }

    // 진행중인 미션 완료로 변경 및 조회
    @Transactional
    public MemberMissionDto.Response completeMission(Long memberMissionId) {
        log.info("Completing mission - memberMissionId: {}", memberMissionId);
        MemberMission memberMission = memberMissionRepository.findById(memberMissionId)
                .orElseThrow(() -> new MissionNotFoundException("MemberMission not found"));

        MemberMission completedMission = MemberMission.builder()
                .id(memberMission.getId())
                .member(memberMission.getMember())
                .mission(memberMission.getMission())
                .status(MemberMissionStatus.COMPLETED)
                .startedAt(memberMission.getStartedAt())
                .completedAt(LocalDateTime.now())
                .deadlineAt(memberMission.getDeadlineAt())
                .reviews(memberMission.getReviews())
                .pointHistories(memberMission.getPointHistories())
                .build();

        memberMissionRepository.save(completedMission);
        return MemberMissionDto.Response.from(completedMission);
    }
}