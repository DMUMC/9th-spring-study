package com.example.dobee.domain.member.controller;

import com.example.dobee.domain.member.dto.response.MyProfileDto;
import com.example.dobee.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/{userId}/profile")
    public ResponseEntity<MyProfileDto> getMyProfile(@PathVariable Long userId) {
        MyProfileDto myProfile = memberService.getMyProfile(userId);

        return ResponseEntity.ok(myProfile);
    }
}
