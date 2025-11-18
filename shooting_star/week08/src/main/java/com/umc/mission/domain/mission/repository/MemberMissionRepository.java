package com.umc.mission.domain.mission.repository;

import com.umc.mission.domain.mission.entity.MemberMission;
import com.umc.mission.domain.mission.enums.MemberMissionStatus;
import jakarta.persistence.LockModeType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    // 특정 회원의 진행 중인 미션 조회 - 메서드 이름 기반 쿼리
    Page<MemberMission> findByMemberIdAndStatus(Long memberId, MemberMissionStatus status, Pageable pageable);

    // 특정 회원의 완료된 미션 조회 - 메서드 이름 기반 쿼리
    Page<MemberMission> findByMemberIdAndStatusOrderByCompletedAtDesc(Long memberId, MemberMissionStatus status, Pageable pageable);

    // 특정 회원의 모든 미션 조회
    Page<MemberMission> findByMemberId(Long memberId, Pageable pageable);

    // 특정 회원의 미션 개수 조회
    Long countByMemberId(Long memberId);

    // 특정 회원의 상태별 미션 개수 조회
    Long countByMemberIdAndStatus(Long memberId, MemberMissionStatus status);

    // 미션 정보를 함께 조회 fetch join 사용
    @Query("SELECT mm FROM MemberMission mm " +
           "JOIN FETCH mm.mission m " +
           "JOIN FETCH m.store s " +
           "WHERE mm.member.id = :memberId AND mm.status = :status")
    Page<MemberMission> findByMemberIdAndStatusWithMissionAndStore(
            @Param("memberId") Long memberId,
            @Param("status") MemberMissionStatus status,
            Pageable pageable
    );
}