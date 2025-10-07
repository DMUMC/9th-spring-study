package com.umc.mission.domain.mission.controller;

import com.umc.mission.domain.mission.entity.MemberMission;
import com.umc.mission.domain.mission.service.MissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/missions/challenges")
@RequiredArgsConstructor
public class MissionController {

    private final MissionService memberMissionService;

    // 락 없이 - 동시성 문제 발생 가능
    @PostMapping("/without-lock")
    public ResponseEntity<MemberMission> challengeMissionWithoutLock(
            @RequestParam Long memberId,
            @RequestParam Long missionId) {
        MemberMission result = memberMissionService.challengeMissionWithoutLock(memberId, missionId);
        return ResponseEntity.ok(result);
    }

    // @Lock 사용 - 동시성 문제 해결
    @PostMapping("/with-lock")
    public ResponseEntity<MemberMission> challengeMissionWithLock(
            @RequestParam Long memberId,
            @RequestParam Long missionId) {
        MemberMission result = memberMissionService.challengeMissionWithLock(memberId, missionId);
        return ResponseEntity.ok(result);
    }
}