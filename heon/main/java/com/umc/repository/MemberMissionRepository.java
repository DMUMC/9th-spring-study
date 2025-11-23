package com.umc.repository;

import com.umc.domain.MemberMission;
import com.umc.domain.enums.MissionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {
    List<MemberMission> findByMemberIdAndStatus(Long memberId, MissionStatus status);
    Optional<MemberMission> findByMemberIdAndMissionId(Long memberId, Long missionId);  // 이거 안쓸 듯 하긴 한데
    boolean existsByMemberIdAndMissionId(Long memberId, Long missionId);
}
