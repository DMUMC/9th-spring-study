package com.example.dobee.global.test.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class TestDto {

    @Builder
    @Getter
    public static class Response {
        private String testing;
    }

    @Builder
    @Setter
    public static class Exception {
        private String testString;
    }
}
