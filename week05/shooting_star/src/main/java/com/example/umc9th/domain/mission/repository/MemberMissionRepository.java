package com.example.umc9th.domain.mission.repository;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.mission.entity.mapping.MemberMission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.awt.print.Pageable;
import java.util.List;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {
    // 커서 페이징을 위한 JPQL 쿼리
    @Query("SELECT mm FROM MemberMission mm " +
            "JOIN FETCH mm.mission m " + // join fetch로 Mission 엔티티를 하나의 쿼리로 함께 가져옴
            "JOIN FETCH m.store s " + // Mission 엔티티를 이용하여 Store 엔티티 데이터도 하나의 쿼리로 함께 가져옴
            "WHERE mm.member = :member AND mm.isComplete = :isComplete AND mm.id < :cursorId " +
            "ORDER BY mm.id desc ")
    List<MemberMission> findMemberMissionsByCursor(
            @Param("member") Member member,
            @Param("isComplete") Boolean isComplete, // 검색 기준이 진행중인지 진행완료인지의 여부
            @Param("cursorId") Long cursorId, // 조회의 기준 커서
            Pageable pageable // limit절(동적으로 Controller 계층에서 정의됨)
            );
}
