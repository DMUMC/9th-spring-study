package com.example.dobee.domain.mission.repository;

import com.example.dobee.domain.member.entity.Member;
import com.example.dobee.domain.mission.entity.Mission;
import com.example.dobee.domain.mission.entity.mapping.MemberMission;
import com.example.dobee.domain.mission.enums.MissionStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {
    boolean existsByMemberAndMission(Member member, Mission mission);
    Page<MemberMission> findAllByMemberAndStatus(Member member, MissionStatus status, Pageable pageable);
}
