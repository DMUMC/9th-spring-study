package com.umc.mission.global.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {

    /**
     * 500 에러를 강제로 발생시키는 테스트 API
     * 디스코드 알림이 정상적으로 전송되는지 확인하기 위한 용도
     */
    @GetMapping("/error-500")
    public void testInternalServerError() {
        throw new RuntimeException("This is a test 500 error for Discord notification");
    }

    /**
     * NullPointerException을 발생시키는 테스트 API
     */
    @GetMapping("/error-npe")
    public void testNullPointerError() {
        String nullString = null;
        nullString.length(); // NullPointerException 발생
    }

    /**
     * ArithmeticException을 발생시키는 테스트 API
     */
    @GetMapping("/error-arithmetic")
    public void testArithmeticError() {
        int result = 10 / 0; // ArithmeticException 발생
    }
}