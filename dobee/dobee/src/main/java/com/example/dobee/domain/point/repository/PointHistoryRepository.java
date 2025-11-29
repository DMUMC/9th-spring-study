package com.example.dobee.domain.point.repository;

import com.example.dobee.domain.member.entity.Member;
import com.example.dobee.domain.point.entity.PointHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PointHistoryRepository extends JpaRepository<PointHistory, Long> {
    Optional<PointHistory> findTopByMemberOrderByCreatedAtDesc(Member member);
}
