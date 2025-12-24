package com.umc.mission.domain.member.service;

import com.umc.mission.domain.member.dto.MemberDto;
import com.umc.mission.domain.member.entity.Member;
import com.umc.mission.domain.member.enums.MemberStatus;
import com.umc.mission.domain.member.exception.MemberAlreadyInactiveException;
import com.umc.mission.domain.member.exception.MemberNotFoundException;
import com.umc.mission.domain.member.repository.MemberRepository;
import com.umc.mission.domain.mission.dto.MemberMissionDto;
import com.umc.mission.domain.mission.enums.MemberMissionStatus;
import com.umc.mission.domain.mission.repository.MemberMissionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberMissionRepository memberMissionRepository;

    // Member Soft Delete
    @Transactional
    public void withdrawMember(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException("Member not found with id: " + memberId));

        if (member.getStatus() != MemberStatus.ACTIVE) {
            throw new MemberAlreadyInactiveException("Member is already inactive");
        }

        // orphanRemoval = true, soft delete라 하나씩 clear.
        log.info("Clearing member missions for memberId: {}", memberId);
        member.getMemberMissions().clear();

        log.info("Clearing reviews for memberId: {}", memberId);
        member.getReviews().clear();

        log.info("Clearing region mission stats for memberId: {}", memberId);
        member.getRegionMissionStats().clear();

        memberRepository.save(member);
    }

    // Member Hard Delete
    @Transactional
    public void deleteMemberPermanently(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException("Member not found with id: " + memberId));

        // @Modifying로 Batch Delete 활용
        log.info("Batch deleting member missions");
        memberRepository.deleteAllMemberMissionsByMemberId(memberId);

        log.info("Batch deleting reviews");
        memberRepository.deleteAllReviewsByMemberId(memberId);

        log.info("Batch deleting region mission stats");
        memberRepository.deleteAllRegionMissionStatsByMemberId(memberId);

        memberRepository.delete(member);
        log.info("Permanent member deletion completed for memberId: {}", memberId);
    }

    // 회원 정보 조회
    @Transactional(readOnly = true)
    public Member getMemberInfo(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException("Member not found with id: " + memberId));
    }

    // 회원의 진행 중인 미션 목록 조회
    @Transactional(readOnly = true)
    public Page<MemberMissionDto.Response> getMissionsOngoing(Long memberId, int page, int size) {
        return memberMissionRepository.findByMemberIdAndStatus(memberId, MemberMissionStatus.ONGOING, PageRequest.of(page, size))
                .map(MemberMissionDto.Response::from);
    }

    // 회원의 완료된 미션 목록 조회
    @Transactional(readOnly = true)
    public Page<MemberMissionDto.Response> getMissionsCompleted(Long memberId, int page, int size) {
        return memberMissionRepository.findByMemberIdAndStatusOrderByCompletedAtDesc(memberId, MemberMissionStatus.COMPLETED, PageRequest.of(page, size))
                .map(MemberMissionDto.Response::from);
    }

    // 회원의 미션 통계 조회
    @Transactional(readOnly = true)
    public MemberDto.MissionStats getMissionStats(Long memberId) {
        Long totalMissions = memberMissionRepository.countByMemberId(memberId);
        Long ongoingMissions = memberMissionRepository.countByMemberIdAndStatus(memberId, MemberMissionStatus.ONGOING);
        Long completedMissions = memberMissionRepository.countByMemberIdAndStatus(memberId, MemberMissionStatus.COMPLETED);

        return MemberDto.MissionStats.builder()
                .total(totalMissions)
                .ongoing(ongoingMissions)
                .completed(completedMissions)
                .build();
    }
}