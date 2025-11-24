package com.umc.mission.domain.mission.service;

import com.umc.mission.domain.member.exception.code.MemberErrorCode;
import com.umc.mission.domain.member.exception.code.MemberException;
import com.umc.mission.domain.member.repository.MemberRepository;
import com.umc.mission.domain.mission.entity.MemberMission;
import com.umc.mission.domain.mission.entity.Mission;
import com.umc.mission.domain.mission.enums.MemberMissionStatus;
import com.umc.mission.domain.mission.repository.MemberMissionRepository;
import com.umc.mission.domain.mission.repository.MissionRepository;
import com.umc.mission.domain.store.exception.StoreErrorCode;
import com.umc.mission.domain.store.exception.StoreHandler;
import com.umc.mission.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MissionQueryServiceImpl implements MissionQueryService {
    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;
    private final MemberMissionRepository memberMissionRepository;
    private final MemberRepository memberRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<Mission> getMissionListByStoreId(Long storeId, Integer page) {
        storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreHandler(StoreErrorCode.NOT_FOUND));

        return missionRepository.findByStoreId(storeId, PageRequest.of(page, 5));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<MemberMission> getMyChallengingMissions(
            Long memberId,
            Integer page
    ){
        memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));

        return memberMissionRepository.findByMemberIdAndStatus(
                memberId,
                MemberMissionStatus.ONGOING,
                PageRequest.of(page, 5)
        );
    }
}
