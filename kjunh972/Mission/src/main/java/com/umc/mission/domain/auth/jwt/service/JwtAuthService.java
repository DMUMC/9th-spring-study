package com.umc.mission.domain.auth.jwt.service;

import com.umc.mission.domain.auth.exception.DuplicateEmailException;
import com.umc.mission.domain.auth.jwt.dto.JwtLoginDto;
import com.umc.mission.domain.auth.jwt.dto.JwtSignupRequest;
import com.umc.mission.domain.member.entity.Member;
import com.umc.mission.domain.member.enums.MemberStatus;
import com.umc.mission.domain.member.repository.MemberRepository;
import com.umc.mission.global.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class JwtAuthService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @Transactional
    public void signup(JwtSignupRequest request) {
        if (memberRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new DuplicateEmailException();
        }

        Member member = Member.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .name(request.getName())
                .socialType("LOCAL")
                .status(MemberStatus.ACTIVE)
                .build();

        memberRepository.save(member);
    }

    public JwtLoginDto.Response login(JwtLoginDto.Request request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        String token = jwtUtil.generateToken(request.getEmail());

        return new JwtLoginDto.Response(token);
    }
}