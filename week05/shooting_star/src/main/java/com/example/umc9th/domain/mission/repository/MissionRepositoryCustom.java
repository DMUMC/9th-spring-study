package com.example.umc9th.domain.mission.repository;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.mission.dto.AvailableMissionDTO;
import com.example.umc9th.domain.mission.entity.Mission;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MissionRepositoryCustom {
    // 내가 만들 QueryDSL 메서드 선언
    // jpa의 기본 기능(findById)와 직접 만들 복잡한 QueryDSL 쿼리를 분리하여 관리하면 코드가 깔끔해짐
    List<AvailableMissionDTO> findAvailableMissionsByRegion(
            Member member,
            String region,
            Long cursorId,
            Pageable pageable
    );
}
