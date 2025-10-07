package com.umc.mission.domain.mission.repository;

import com.umc.mission.domain.mission.entity.MemberMission;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    // 락 없이 조회 (동시성 문제 발생 가능)
    @Query("SELECT mm FROM MemberMission mm WHERE mm.member.id = :memberId AND mm.mission.id = :missionId")
    Optional<MemberMission> findByMemberIdAndMissionId(
            @Param("memberId") Long memberId,
            @Param("missionId") Long missionId
    );

    // Pessimistic Write Lock (동시성 문제 해결)
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT mm FROM MemberMission mm WHERE mm.member.id = :memberId AND mm.mission.id = :missionId")
    Optional<MemberMission> findByMemberIdAndMissionIdWithLock(
            @Param("memberId") Long memberId,
            @Param("missionId") Long missionId
    );
}