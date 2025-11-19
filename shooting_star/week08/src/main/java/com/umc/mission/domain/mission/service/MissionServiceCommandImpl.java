package com.umc.mission.domain.mission.service;

import com.umc.mission.domain.member.entity.Member;
import com.umc.mission.domain.member.exception.code.MemberErrorCode;
import com.umc.mission.domain.member.exception.code.MemberException;
import com.umc.mission.domain.member.repository.MemberRepository;
import com.umc.mission.domain.mission.dto.MissionDto2;
import com.umc.mission.domain.mission.entity.MemberMission;
import com.umc.mission.domain.mission.entity.Mission;
import com.umc.mission.domain.mission.enums.MemberMissionStatus;
import com.umc.mission.domain.mission.repository.MemberMissionRepository;
import com.umc.mission.domain.mission.repository.MissionRepository;
import com.umc.mission.global.apiPayload.code.GeneralErrorCode;
import com.umc.mission.global.apiPayload.exception.GeneralException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MissionServiceCommandImpl implements MissionServiceCommand {
    private final MissionRepository missionRepository;
    private final MemberRepository memberRepository;
    private final MemberMissionRepository memberMissionRepository;

    @Override
    @Transactional
    public MemberMission challengeMission(
        Long memberId,
        Long missionId,
        MissionDto2.ChallengeRequestDTO request
    ){
        // 회원 조회
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));

        // 미션 조회
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.NOT_FOUND));

        boolean isExist = memberMissionRepository.existsByMemberIdAndMissionIdAndStatus(
                missionId,
                missionId,
                MemberMissionStatus.ONGOING
        );

        if(isExist){
            throw new GeneralException(GeneralErrorCode.BAD_REQUEST);
        }

        MemberMission memberMission = request.toEntity(member, mission);

        return memberMissionRepository.save(memberMission);
    }
}
