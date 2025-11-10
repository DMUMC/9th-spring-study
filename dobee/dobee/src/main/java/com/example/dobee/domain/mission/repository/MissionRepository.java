package com.example.dobee.domain.mission.repository;

import com.example.dobee.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MissionRepository extends JpaRepository<Mission, Long> {

    // 특정 지역의 활성화된 미션 조회 (페이징) - @Query 어노테이션 사용
    @Query("SELECT m FROM Mission m " +
            "JOIN m.store s " +
            "WHERE s.region.id = :regionId " +
            "AND m.status = 'active' " +
            "ORDER BY m.createdAt DESC")
    Page<Mission> findActiveByRegionId(@Param("regionId") Long regionId, Pageable pageable);

    // 특정 지역의 활성화된 미션 조회 (스토어 정보 포함, fetch join) - @Query 어노테이션 사용
    @Query("SELECT DISTINCT m FROM Mission m " +
            "JOIN FETCH m.store s " +
            "JOIN FETCH s.region r " +
            "WHERE r.id = :regionId " +
            "AND m.status = 'active' " +
            "ORDER BY m.createdAt DESC")
    Page<Mission> findActiveByRegionIdWithStore(@Param("regionId") Long regionId, Pageable pageable);

    // 회원이 아직 도전하지 않은 미션 조회 (특정 지역) - @Query 어노테이션 사용
    @Query("SELECT m FROM Mission m " +
            "JOIN m.store s " +
            "WHERE s.region.id = :regionId " +
            "AND m.status = 'active' " +
            "AND m.id NOT IN (" +
            "    SELECT mm.mission.id FROM MemberMission mm " +
            "    WHERE mm.member.id = :memberId" +
            ") " +
            "ORDER BY m.createdAt DESC")
    Page<Mission> findAvailableMissionsByRegionAndMember(
            @Param("regionId") Long regionId,
            @Param("memberId") Long memberId,
            Pageable pageable
    );

    // 특정 스토어의 활성화된 미션 조회 (페이징) - 메서드 이름 기반 쿼리
    Page<Mission> findByStoreIdAndStatus(Long storeId, String status, Pageable pageable);

    // 미션 상태별 조회 (페이징) - 메서드 이름 기반 쿼리
    Page<Mission> findByStatus(String status, Pageable pageable);

    // 미션 개수 조회
    Long countByStatus(String status);
}
