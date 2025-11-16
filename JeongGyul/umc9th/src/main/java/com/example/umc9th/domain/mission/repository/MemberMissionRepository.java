package com.example.umc9th.domain.mission.repository;

import com.example.umc9th.domain.mission.entity.MemberMission;
import com.example.umc9th.domain.mission.enums.MissionStatus;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MemberMissionRepository extends JpaRepository<MemberMission,Long> {
    @EntityGraph(attributePaths = "mission")
    List<MemberMission> findByMember_IdAndStatus(Long memberId, MissionStatus status, Pageable pageable);

    @Query("""
           SELECT COUNT(mm) FROM MemberMission mm
           WHERE mm.member.id = :memberId
           AND mm.status = com.example.umc9th.domain.mission.enums.MissionStatus.SUCCEEDED
           AND mm.mission.store.region.id = :regionId
           """)
    Integer countSucceededMissionsByRegion(
            @Param("memberId") Long memberId,
            @Param("regionId") Long regionId
    );
}