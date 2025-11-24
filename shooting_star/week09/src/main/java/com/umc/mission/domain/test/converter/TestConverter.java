package com.umc.mission.domain.test.converter;

import com.umc.mission.domain.test.dto.res.TestResDTO;
import org.aspectj.weaver.ast.Test;

public class TestConverter {
    // 자바 객체 -> dto
    public static TestResDTO.Testing toTestingDTO(String testing){
        return TestResDTO.Testing.builder().testing(testing).build();
    }

    public static TestResDTO.Exception toExceptionDTO(String testing){
        return TestResDTO.Exception.builder()
                .testString(testing)
                .build();
    }
}
