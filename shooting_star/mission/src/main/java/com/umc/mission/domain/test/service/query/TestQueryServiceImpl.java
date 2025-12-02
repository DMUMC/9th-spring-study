package com.umc.mission.domain.test.service.query;

import com.umc.mission.domain.test.exception.TestException;
import com.umc.mission.domain.test.exception.code.TestErrorCode;
import org.springframework.stereotype.Service;

@Service
public class TestQueryServiceImpl implements TestQueryService {
    @Override
    public void checkFlag(Long flag) {
        if(flag==1){
            throw new TestException(TestErrorCode.TEST_EXCEPTION);
        }
    }
}
