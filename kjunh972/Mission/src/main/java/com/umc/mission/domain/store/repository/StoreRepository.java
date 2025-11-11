package com.umc.mission.domain.store.repository;

import com.umc.mission.domain.store.entity.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long>, StoreRepositoryCustom {

    // 특정 지역의 가게 조회 (페이징) - 메서드 이름 기반 쿼리
    Page<Store> findByRegionId(Long regionId, Pageable pageable);

    // 특정 카테고리의 가게 조회 (페이징) - 메서드 이름 기반 쿼리
    Page<Store> findByCategoryId(Long categoryId, Pageable pageable);

    // 가게 이름으로 검색 (페이징) - 메서드 이름 기반 쿼리
    Page<Store> findByNameContaining(String name, Pageable pageable);

    // 특정 지역의 가게 목록 조회 (리스트)
    List<Store> findByRegionId(Long regionId);

    // 평점 높은 순으로 가게 조회
    @Query("SELECT s FROM Store s WHERE s.region.id = :regionId ORDER BY s.rating DESC, s.reviewCount DESC")
    Page<Store> findTopRatedByRegion(@Param("regionId") Long regionId, Pageable pageable);
}