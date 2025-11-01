package com.example.missionreview.repository;

import com.example.missionreview.entity.UserMission;
import com.example.missionreview.entity.MissionInfo;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMissionRepo extends JpaRepository<UserMission, Long> {
    @Query("SELECT tum, tmi FROM UserMission tum JOIN MissionInfo tmi ON tum.missionCode = tmi.code " +
            "WHERE tum.userCode = :userCode ORDER BY tum.code DESC")
    List<Object[]> findUserMissions(@Param("userCode") Long userCode, Pageable pageable);
}