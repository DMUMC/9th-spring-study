package com.example.dongdong.domain.review.repository;

import com.example.dongdong.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    // 특정 회원이 작성한 리뷰 조회
    @Query("SELECT r FROM Review r WHERE r.member.id = :memberId")
    List<Review> findByMemberId(@Param("memberId") Long memberId);
}
