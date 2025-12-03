package com.example.dobee.global.test.controller;

import com.example.dobee.global.apiPayload.ApiResponse;
import com.example.dobee.global.apiPayload.code.GeneralSuccessCode;
import com.example.dobee.global.test.converter.TestConverter;
import com.example.dobee.global.test.dto.TestDto;
import com.example.dobee.global.test.service.query.TestQueryService;
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
    public ApiResponse<TestDto.Response> test() throws Exception {
        GeneralSuccessCode code = GeneralSuccessCode.OK;
        return ApiResponse.onSuccess(
                code,
                TestConverter.toTestingDto("This is Test!")
        );
    }

    @GetMapping("/exception")
    public ApiResponse<TestDto.Exception> exception(
            @RequestParam Long flag
    ) {

        testQueryService.checkFlag(flag);

        GeneralSuccessCode code = GeneralSuccessCode.OK;
        return ApiResponse.onSuccess(code, TestConverter.toExceptionDto("This is Test"));
    }
}
