package com.example.dongdong.domain.mission.repository;

import com.example.dongdong.domain.mission.entity.Mission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    // 완료되지 않은 미션 조회
    @Query("SELECT m FROM Mission m WHERE m.isComplete = false")
    List<Mission> findIncompleteMissions();

    // 특정 가게의 미션 목록 조회
    @Query("SELECT m FROM Mission m WHERE m.store.id = :storeId")
    List<Mission> findByStoreId(@Param("storeId") Long storeId);
}