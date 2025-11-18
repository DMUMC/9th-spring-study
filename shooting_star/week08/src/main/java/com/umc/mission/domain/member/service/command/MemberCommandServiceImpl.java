package com.umc.mission.domain.member.service.command;

import com.umc.mission.domain.member.converter.MemberConverter;
import com.umc.mission.domain.member.dto.MemberReqDTO;
import com.umc.mission.domain.member.dto.MemberResDTO;
import com.umc.mission.domain.member.entity.Member;
import com.umc.mission.domain.member.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberCommandServiceImpl implements MemberCommandService {
    private final MemberRepository memberRepository;

    // 회원가입
    @Override
    @Transactional
    public MemberResDTO.JoinDTO signUp(
            MemberReqDTO.JoinDTO dto
    ){
        // 사용자 생성
        Member member = MemberConverter.toMember(dto);

        dto.preferCategory().forEach(member::addPreferredFood);

        Member saveMember = memberRepository.save(member);

        // 응답 dto 생성
        return MemberConverter.toJoinDTO(saveMember);
    }
}
