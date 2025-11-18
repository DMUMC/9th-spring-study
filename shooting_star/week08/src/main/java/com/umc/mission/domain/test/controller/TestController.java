package com.umc.mission.domain.test.controller;

import com.umc.mission.domain.test.converter.TestConverter;
import com.umc.mission.domain.test.dto.res.TestResDTO;
import com.umc.mission.domain.test.exception.TestException;
import com.umc.mission.domain.test.exception.code.TestErrorCode;
import com.umc.mission.domain.test.service.query.TestQueryService;
import com.umc.mission.global.apiPayload.ApiResponse;
import com.umc.mission.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/temp")
public class TestController {

    private final TestQueryService testQueryService;

    @GetMapping("/test")
    public ApiResponse<TestResDTO.Testing> test() throws Exception{
        GeneralSuccessCode code = GeneralSuccessCode.OK;

        return ApiResponse.onSuccess(
                code,
                TestConverter.toTestingDTO("this is test!"));
    }

    @GetMapping("/exception")
    public ApiResponse<TestResDTO.Exception> exception(
            @RequestParam Long flag
    ){
        testQueryService.checkFlag(flag);

        // 응답 코드 정의
        GeneralSuccessCode code = GeneralSuccessCode.OK;

        return ApiResponse.onSuccess(code, TestConverter.toExceptionDTO("this is test!"));
    }
}
