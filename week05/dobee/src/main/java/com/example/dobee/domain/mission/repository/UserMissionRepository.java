package com.example.dobee.domain.mission.repository;

import com.example.dobee.domain.member.entity.Member;
import com.example.dobee.domain.mission.entity.Mission;
import com.example.dobee.domain.mission.entity.UserMission;
import com.example.dobee.domain.mission.entity.UserMissionStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface UserMissionRepository extends JpaRepository<UserMission,Long> {

    List<UserMission> findByMember(Member member);

    @Query("SELECT um FROM UserMission um JOIN FETCH um.mission m JOIN FETCH m.store WHERE um.member = :member AND um.status = :status")
    Page<UserMission> findByMemberAndStatus(@Param("member") Member member, @Param("status")UserMissionStatus status, Pageable pageable);

    @Query("SELECT m FROM Mission m JOIN FETCH m.store s" +
            " WHERE m.isActive = true " +
            "AND s.latitude BETWEEN :minLat AND :maxLat " +
    "AND s.longitude BETWEEN :minLng AND :maxLng")
    Page<Mission> findAvailableMissionsInRegion(
            @Param("minLat") BigDecimal minLat,
            @Param("maxLat") BigDecimal maxLat,
            @Param("minLng") BigDecimal minLng,
            @Param("maxLng") BigDecimal maxLng,
            Pageable pageable
    );
}
