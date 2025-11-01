package com.example.judy.domain.mission.repository;

import com.example.judy.domain.mission.entity.Mission;
import org.springframework.data.domain.Pageable; // Pageable 임포트 추가
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
import java.util.List;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    @Query(value = """
        SELECT m
        FROM Mission m
        LEFT JOIN FETCH m.store s
        LEFT JOIN s.location l
        WHERE l.id = :locationId
          AND m.deadline >= :deadline
          AND m.id < :cursorId
          AND NOT EXISTS (
              SELECT 1
              FROM MemberMission mm
              WHERE mm.member.id = :memberId
                AND mm.mission.id = m.id
          )
        ORDER BY m.id DESC
        """)
    List<Mission> searchAvailableMissions(
            @Param("locationId") Long locationId,
            @Param("memberId") Long memberId,
            @Param("cursorId") Long cursorId,
            @Param("deadline") LocalDate deadline,
            Pageable pageable
    );
}