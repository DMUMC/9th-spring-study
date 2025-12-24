package com.example.dobee.global.test.converter;

import com.example.dobee.global.test.dto.TestDto;

public class TestConverter {

    public static TestDto.Response toTestingDto(String testing) {
        return TestDto.Response.builder()
                .testing(testing)
                .build();
    }

    public static TestDto.Exception toExceptionDto(String testString) {
        return TestDto.Exception.builder()
                .testString(testString)
                .build();
    }
}
