package com.umc.mission.domain.member.service;

import com.umc.mission.domain.member.entity.Member;
import com.umc.mission.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    // Member Soft Delete
    @Transactional
    public void withdrawMember(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Member not found with id: " + memberId));

        if (!"active".equals(member.getStatus())) {
            throw new IllegalStateException("Member is already inactive");
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
                .orElseThrow(() -> new IllegalArgumentException("Member not found with id: " + memberId));

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
}