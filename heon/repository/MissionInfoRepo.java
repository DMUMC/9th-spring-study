package com.example.missionreview.repository;

import com.example.missionreview.entity.MissionInfo;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MissionInfoRepo extends JpaRepository<MissionInfo, Long> {

    @Query("SELECT tmi FROM MissionInfo tmi " +
            "LEFT JOIN UserMission tum ON tum.missionCode = tmi.code AND tum.userCode = :userCode " +
            "LEFT JOIN UserInfo tui ON tmi.code = tui.code " +
            "LEFT JOIN AddressInfo tadi ON tmi.code = tadi.code " +
            "WHERE tadi.code = :regionCode AND tum.missionCode IS NULL " +
            "ORDER BY tmi.code DESC")
    List<MissionInfo> findAvailableMissions(@Param("userCode") Long userCode,
                                            @Param("regionCode") Long regionCode);
}