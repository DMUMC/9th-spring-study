package com.example.umc9th.domain.mission.repository;

import com.example.umc9th.domain.mission.entity.Mission;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MissionRepository extends JpaRepository<Mission,Long> {
    @Query("""
           SELECT m FROM Mission m
           JOIN FETCH m.store s
           WHERE m.store.region.id = :regionId
           AND NOT EXISTS (
               SELECT mm FROM MemberMission mm
               WHERE mm.member.id = :memberId AND mm.mission = m
           )
           """)
    List<Mission> findAvailableMissionsByRegion(
            @Param("regionId") Long regionId,
            @Param("memberId") Long memberId,
            Pageable pageable
    );
}