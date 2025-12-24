package com.example.umc9th.domain.mission.repository;

import com.example.umc9th.domain.mission.entity.UserMission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserMissionRepository extends JpaRepository<UserMission, Long> {
    Page<UserMission> findAllByMemberIdAndStatus(Long memberId, String status, Pageable pageable);
}
