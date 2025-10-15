package com.example.dobee.domain.member.service;

import com.example.dobee.domain.member.dto.response.MyMissionDto;
import com.example.dobee.domain.member.dto.response.MyProfileDto;
import com.example.dobee.domain.member.entity.Member;
import com.example.dobee.domain.member.repository.MemberRepository;
import com.example.dobee.domain.mission.dto.response.AvailableMissionDto;
import com.example.dobee.domain.mission.entity.Mission;
import com.example.dobee.domain.mission.entity.UserMission;
import com.example.dobee.domain.mission.entity.UserMissionStatus;
import com.example.dobee.domain.mission.repository.UserMissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final UserMissionRepository userMissionRepository;

    @Transactional(readOnly = true)
    public MyProfileDto getMyProfile(Long userId) {
        Member member = memberRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        return MyProfileDto.from(member);
    }

    @Transactional(readOnly = true)
    public Page<MyMissionDto> getMissions(Long userId, UserMissionStatus status, Pageable pageable) {
        Member member = memberRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        Page<UserMission> userMissionsPage = userMissionRepository.findByMemberAndStatus(member, status, pageable);

        return userMissionsPage.map(MyMissionDto::from);
    }

    @Transactional(readOnly = true)
    public Page<AvailableMissionDto> getAvailableMissions(BigDecimal minLat, BigDecimal maxLat,
                                                          BigDecimal minLng, BigDecimal maxLng,
                                                          Pageable pageable) {
        Page<Mission> missionPage = userMissionRepository.findAvailableMissionsInRegion(
                minLat, maxLat, minLng, maxLng, pageable);

        return missionPage.map(AvailableMissionDto::from);
    }


}
