package com.example.dobee.domain.member.service.query;

import com.example.dobee.domain.member.converter.MemberConverter;
import com.example.dobee.domain.member.dto.MemberDto;
import com.example.dobee.domain.member.entity.Member;
import com.example.dobee.domain.member.exception.MemberException;
import com.example.dobee.domain.member.exception.code.MemberErrorCode;
import com.example.dobee.domain.member.repository.MemberRepository;
import com.example.dobee.global.auth.service.CustomUserDetails;
import com.example.dobee.global.auth.service.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberQueryServiceImpl implements MemberQueryService{

    private final MemberRepository memberRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder encoder;

    @Override
    public MemberDto.LoginResDto login(MemberDto.@Valid LoginReqDto dto) {

        Member member = memberRepository.findByEmail(dto.email())
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        if (!encoder.matches(dto.password(), member.getPassword())) {
            throw new MemberException(MemberErrorCode.INVALID_PASSWORD);
        }

        CustomUserDetails userDetails = new CustomUserDetails(member);

        String accessToken = jwtUtil.createAccessToken(userDetails);

        return MemberConverter.toLoginDto(member, accessToken);
    }
}
