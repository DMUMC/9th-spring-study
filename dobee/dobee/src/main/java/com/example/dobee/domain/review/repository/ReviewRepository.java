package com.example.dobee.domain.review.repository;

import com.example.dobee.domain.review.entity.Review;
import com.example.dobee.domain.review.enums.ReviewStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewRepositoryCustom {

    // 특정 회원의 리뷰 목록 조회
    Page<Review> findByMemberId(Long memberId, Pageable pageable);

    // 특정 가게의 리뷰 목록 조회
    Page<Review> findByStoreId(Long storeId, Pageable pageable);

    // 특정 회원의 리뷰 개수 조회
    Long countByMemberId(Long memberId);

    // 특정 가게의 리뷰 개수 조회
    Long countByStoreId(Long storeId);

    // 특정 회원과 가게의 리뷰 조회
    List<Review> findByMemberIdAndStoreId(Long memberId, Long storeId);

    // 활성화된 리뷰만 조회
    @Query("SELECT r FROM Review r WHERE r.member.id = :memberId AND r.status = :status")
    Page<Review> findByMemberIdAndStatus(@Param("memberId") Long memberId,
                                         @Param("status") ReviewStatus status,
                                         Pageable pageable);
}
