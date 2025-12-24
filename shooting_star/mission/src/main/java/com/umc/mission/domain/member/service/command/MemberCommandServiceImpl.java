package com.umc.mission.domain.member.service.command;

import com.umc.mission.domain.member.converter.MemberConverter;
import com.umc.mission.domain.member.dto.MemberReqDTO;
import com.umc.mission.domain.member.dto.MemberResDTO;
import com.umc.mission.domain.member.entity.Member;
import com.umc.mission.domain.member.repository.MemberRepository;
import com.umc.mission.global.auth.enums.Role;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberCommandServiceImpl implements MemberCommandService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    // 회원가입
    @Override
    @Transactional
    public MemberResDTO.JoinDTO signUp(
            MemberReqDTO.JoinDTO dto
    ){
        // 솔트된 비밀번호 생성
        String salt = passwordEncoder.encode(dto.password());

        // 사용자 생성(유저/관리자는 따로 API를 만들어서 관리)
        Member member = MemberConverter.toMember(dto, salt, Role.ROLE_USER);

        dto.preferCategory().forEach(member::addPreferredFood);

        Member saveMember = memberRepository.save(member);

        // 응답 dto 생성
        return MemberConverter.toJoinDTO(saveMember);
    }
}
