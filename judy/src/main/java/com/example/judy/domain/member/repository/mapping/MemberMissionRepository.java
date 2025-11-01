package com.example.judy.domain.member.repository.mapping;

import com.example.judy.domain.member.entity.mapping.MemberMission;
import com.example.judy.domain.mission.enums.MissionStatus;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    @Query("""
        SELECT mm 
        FROM MemberMission mm
        JOIN mm.mission m
        WHERE mm.member.id = :memberId
          AND mm.missionStatus IN :status
          AND mm.id < :lastId
        ORDER BY mm.id DESC
    """)
    List<MemberMission> findMemberMissionsWithCursor(
            @Param("memberId") Long memberId,
            @Param("status") List<MissionStatus> status,
            @Param("lastId") Long lastId,
            Pageable pageable
    );
}
