package com.example.umc9th.domain.mission.controller;

import com.example.umc9th.domain.mission.dto.req.MissionReqDTO;
import com.example.umc9th.domain.mission.dto.res.MissionResDTO;
import com.example.umc9th.domain.mission.service.command.MemberMissionCommandService;
import com.example.umc9th.global.ApiPayload.ApiResponse;
import com.example.umc9th.global.ApiPayload.code.GeneralSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/missions")
public class MissionController {

    private final MemberMissionCommandService memberMissionCommandService;

    @PostMapping("/challenges")
    public ApiResponse<MissionResDTO.AddMemberMissionDTO> addMemberMission(
            @RequestBody @Valid MissionReqDTO.AddMemberMissionDTO dto
    ) {
            GeneralSuccessCode code = GeneralSuccessCode.CREATED;
            return ApiResponse.onSuccess(code, memberMissionCommandService.addMemberMission(dto));
    }
}