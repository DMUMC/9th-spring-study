package com.umc.service;

import com.umc.converter.MemberMissionConverter;
import com.umc.domain.Member;
import com.umc.domain.MemberMission;
import com.umc.domain.Mission;
import com.umc.domain.enums.MissionStatus;
import com.umc.dto.request.MemberMissionRequestDto;
import com.umc.dto.response.MemberMissionResponseDTO;
import com.umc.dto.response.SimpleMemberMissionResponseDto;
import com.umc.repository.MemberMissionRepository;
import com.umc.repository.MemberRepository;
import com.umc.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberMissionService {
    
    private final MemberMissionRepository memberMissionRepository;
    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;
    
    // 하드코딩된 회원 ID (실제로는 로그인 기능에서 가져와야 함)
    private static final Long HARDCODED_MEMBER_ID = 1L;
    
    @Transactional
    public SimpleMemberMissionResponseDto challengeMission(MemberMissionRequestDto requestDto) {
        // 1. 하드코딩된 회원 조회
        Member member = memberRepository.findById(HARDCODED_MEMBER_ID)
                .orElseThrow(() -> new IllegalArgumentException("회원을 찾을 수 없습니다. ID: " + HARDCODED_MEMBER_ID));
        
        // 2. 미션 조회
        Mission mission = missionRepository.findById(requestDto.getMissionId())
                .orElseThrow(() -> new IllegalArgumentException("미션을 찾을 수 없습니다. ID: " + requestDto.getMissionId()));
        
        // 3. 이미 도전 중인 미션인지 확인
        if (memberMissionRepository.existsByMemberIdAndMissionId(HARDCODED_MEMBER_ID, requestDto.getMissionId())) {
            throw new IllegalStateException("이미 도전 중이거나 완료한 미션입니다.");
        }
        
        // 4. 회원 미션 생성
        MemberMission memberMission = MemberMission.builder()
                .status(MissionStatus.CHALLENGING)
                .build();
        
        memberMission.setMember(member);
        memberMission.setMission(mission);
        
        // 5. 저장
        MemberMission savedMemberMission = memberMissionRepository.save(memberMission);
        
        return SimpleMemberMissionResponseDto.from(savedMemberMission);
    }
    
    public List<SimpleMemberMissionResponseDto> getMyChallengingMissions() {
        return memberMissionRepository.findByMemberIdAndStatus(HARDCODED_MEMBER_ID, MissionStatus.CHALLENGING).stream()
                .map(SimpleMemberMissionResponseDto::from)
                .collect(Collectors.toList());
    }
    
    public List<SimpleMemberMissionResponseDto> getMyCompletedMissions() {
        return memberMissionRepository.findByMemberIdAndStatus(HARDCODED_MEMBER_ID, MissionStatus.COMPLETE).stream()
                .map(SimpleMemberMissionResponseDto::from)
                .collect(Collectors.toList());
    }
    
    @Transactional
    public SimpleMemberMissionResponseDto completeMission(Long memberMissionId) {
        MemberMission memberMission = memberMissionRepository.findById(memberMissionId)
                .orElseThrow(() -> new IllegalArgumentException("회원 미션을 찾을 수 없습니다. ID: " + memberMissionId));
        
        if (!memberMission.getMember().getId().equals(HARDCODED_MEMBER_ID)) {
            throw new IllegalStateException("본인의 미션만 완료할 수 있습니다.");
        }
        
        memberMission.complete();
        
        return SimpleMemberMissionResponseDto.from(memberMission);
    }
    
    /**
     * 내가 진행중인 미션 목록 (페이징)
     */
    public MemberMissionResponseDTO.MemberMissionPreviewListDTO getMyChallengingMissionsPaged(Long memberId, Integer page) {
        memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("회원을 찾을 수 없습니다. ID: " + memberId));
        
        PageRequest pageRequest = PageRequest.of(page - 1, 10, Sort.by(Sort.Direction.DESC, "createdAt"));
        Page<MemberMission> memberMissionPage = memberMissionRepository.findByMemberIdAndStatus(
                memberId, MissionStatus.CHALLENGING, pageRequest);
        
        return MemberMissionConverter.toMemberMissionPreviewListDTO(memberMissionPage);
    }
}
