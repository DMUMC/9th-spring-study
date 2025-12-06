package com.umc.mission.domain.member.service.query;

import com.umc.mission.domain.member.converter.MemberConverter;
import com.umc.mission.domain.member.dto.MemberReqDTO;
import com.umc.mission.domain.member.dto.MemberResDTO;
import com.umc.mission.domain.member.entity.Member;
import com.umc.mission.domain.member.exception.code.MemberErrorCode;
import com.umc.mission.domain.member.exception.code.MemberException;
import com.umc.mission.domain.member.repository.MemberRepository;
import com.umc.mission.global.auth.CustomUserDetails;
import com.umc.mission.global.auth.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberQueryServiceImpl implements MemberQueryService {
    private final MemberRepository memberRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    @Override
    public MemberResDTO.LoginDTO login(
            MemberReqDTO.@Valid LoginDTO dto
    ){
        Member member = memberRepository.findByEmail(dto.email())
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));

        // 비밀번호 검증
        if(!passwordEncoder.matches(dto.password(), member.getPassword())){
            throw new MemberException(MemberErrorCode.INVALID);
        }

        // Jwt 토큰 발급용 UserDetails
        CustomUserDetails userDetails = new CustomUserDetails(member);

        // 액세스 토큰 발급
        String accessToken = jwtUtil.createAccessToken(userDetails);

        // DTO 조립
        return MemberConverter.toLoginDTO(member, accessToken);
    }
}
