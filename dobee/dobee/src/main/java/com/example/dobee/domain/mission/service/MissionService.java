package com.example.dobee.domain.mission.service;

import com.example.dobee.domain.member.entity.Member;
import com.example.dobee.domain.member.exception.MemberNotFoundException;
import com.example.dobee.domain.member.repository.MemberRepository;
import com.example.dobee.domain.mission.dto.MemberMissionDto;
import com.example.dobee.domain.mission.dto.MissionDto;
import com.example.dobee.domain.mission.entity.MemberMission;
import com.example.dobee.domain.mission.entity.Mission;
import com.example.dobee.domain.mission.enums.MemberMissionStatus;
import com.example.dobee.domain.mission.enums.MissionStatus;
import com.example.dobee.domain.mission.exception.AlreadyChallengedException;
import com.example.dobee.domain.mission.exception.MissionNotFoundException;
import com.example.dobee.domain.mission.exception.NotMyMissionException;
import com.example.dobee.domain.mission.repository.MemberMissionRepository;
import com.example.dobee.domain.mission.repository.MissionRepository;
import com.example.dobee.domain.point.entity.PointHistory;
import com.example.dobee.domain.point.repository.PointHistoryRepository;
import com.example.dobee.domain.review.exception.StoreNotFoundException;
import com.example.dobee.domain.store.entity.Store;
import com.example.dobee.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    private final StoreRepository storeRepository;
    private final PointHistoryRepository pointHistoryRepository;

    @Transactional
    public MissionDto.Response addMission(Long storeId, MissionDto.AddMissionRequest request) {

        Store store = storeRepository.findById(storeId)
                .orElseThrow(StoreNotFoundException::new);

        Mission newMission = request.toEntity(store);
        Mission savedMission = missionRepository.save(newMission);

        return MissionDto.Response.from(savedMission);
    }

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
    public MemberMissionDto.Response challengeMissionWithLock(Long memberId, Long missionId) {
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

        MemberMission savedMemberMission = memberMissionRepository.save(memberMission);

        return MemberMissionDto.Response.from(savedMemberMission);
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

    @Transactional(readOnly = true)
    public Page<MissionDto.Response> getMissionsByStore(Long storeId, int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return missionRepository.findByStoreIdAndStatus(storeId, MissionStatus.ACTIVE.name(), pageable)
                .map(MissionDto.Response::from);
    }

    @Transactional(readOnly = true)
    public Page<MemberMissionDto.Response> getOngoingMissions(Long memberId, int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return memberMissionRepository.findByMemberIdAndStatus(memberId, MemberMissionStatus.ONGOING, pageable)
                .map(MemberMissionDto.Response::from);
    }

    @Transactional
    public MemberMissionDto.Response completeMission(Long memberId, Long memberMissionId) {
        MemberMission memberMission = memberMissionRepository.findById(memberMissionId)
                .orElseThrow(() -> new MissionNotFoundException("해당 미션을 찾을 수 없습니다."));

        if (!memberMission.getMember().getId().equals(memberId)) {
            throw new NotMyMissionException("자신이 진행중인 미션만 완료할 수 있습니다.");
        }

        memberMission.complete();

        Integer currentPoint = pointHistoryRepository.findTopByMemberOrderByCreatedAtDesc(memberMission.getMember())
                .map(PointHistory::getPointBalance)
                .orElse(0);

        Integer rewardPoint = memberMission.getMission().getRewardPoint();
        Integer finalPoint = currentPoint + rewardPoint;

        PointHistory pointHistory = PointHistory.builder()
                .member(memberMission.getMember())
                .memberMission(memberMission)
                .pointChange(rewardPoint)
                .pointBalance(finalPoint)
                .type("EARN")
                .description("미션 완료 보상")
                .build();
        pointHistoryRepository.save(pointHistory);

        return MemberMissionDto.Response.from(memberMission);
    }
}
