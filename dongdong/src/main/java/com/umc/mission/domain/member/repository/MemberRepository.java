package com.umc.mission.domain.member.repository;

import com.umc.mission.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    @Modifying
    @Query("DELETE FROM MemberMission mm WHERE mm.member.id = :memberId")
    void deleteAllMemberMissionsByMemberId(@Param("memberId") Long memberId);

    @Modifying
    @Query("DELETE FROM Review r WHERE r.member.id = :memberId")
    void deleteAllReviewsByMemberId(@Param("memberId") Long memberId);

    @Modifying
    @Query("DELETE FROM RegionMissionStats rms WHERE rms.member.id = :memberId")
    void deleteAllRegionMissionStatsByMemberId(@Param("memberId") Long memberId);

    Optional<Member> findByEmail(String email);
}