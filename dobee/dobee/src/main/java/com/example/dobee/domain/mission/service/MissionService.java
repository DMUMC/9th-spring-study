package com.example.dobee.domain.mission.service;

import com.example.dobee.domain.member.entity.Member;
import com.example.dobee.domain.member.repository.MemberRepository;
import com.example.dobee.domain.mission.converter.MissionConverter;
import com.example.dobee.domain.mission.dto.MissionDto;
import com.example.dobee.domain.mission.entity.Mission;
import com.example.dobee.domain.mission.entity.mapping.MemberMission;
import com.example.dobee.domain.mission.enums.MissionStatus;
import com.example.dobee.domain.mission.exception.MissionException;
import com.example.dobee.domain.mission.exception.code.MissionErrorCode;
import com.example.dobee.domain.mission.repository.MemberMissionRepository;
import com.example.dobee.domain.mission.repository.MissionRepository;
import com.example.dobee.domain.store.entity.Store;
import com.example.dobee.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MissionService {

    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;
    private final MemberMissionRepository memberMissionRepository;

    public Mission addMission(MissionDto.AddMissionReqDto request) {
        Store store = storeRepository.findById(request.storeId()).get();
        Mission newMission = MissionConverter.toMission(request, store);
        return missionRepository.save(newMission);
    }

    public MemberMission challengeMission(MissionDto.ChallengeMissionReqDto request) {
        Member member = memberRepository.findById(request.memberId()).get();
        Mission mission = missionRepository.findById(request.missionId()).get();

        if (memberMissionRepository.existsByMemberAndMission(member, mission)) {
            throw new MissionException(MissionErrorCode.ALREADY_CHALLENGING_MISSION);
        }

        MemberMission newMemberMission = MissionConverter.toMemberMission(member, mission);
        return memberMissionRepository.save(newMemberMission);
    }

    public MemberMission completeMission(Long memberMissionId) {
        MemberMission memberMission = memberMissionRepository.findById(memberMissionId)
                .orElseThrow(() -> new MissionException(MissionErrorCode.MEMBER_MISSION_NOT_FOUND));
        memberMission.complete();
        return memberMission;
    }

    @Transactional(readOnly = true)
    public MissionDto.MissionPreviewListDto getMissionList(Long storeId, Integer page) {
        Store store = storeRepository.findById(storeId).get();
        Page<Mission> missionPage = missionRepository.findAllByStore(store, PageRequest.of(page - 1, 10));
        return MissionConverter.toMissionPreviewListDto(missionPage);
    }

    @Transactional(readOnly = true)
    public MissionDto.MyChallengingMissionListDto getMyChallengingMissions(Long memberId, Integer page) {
        Member member = memberRepository.findById(memberId).get();
        Page<MemberMission> missionPage = memberMissionRepository.findAllByMemberAndStatus(member, MissionStatus.CHALLENGING, PageRequest.of(page - 1, 10));
        return MissionConverter.toMyChallengingMissionListDto(missionPage);
    }
}