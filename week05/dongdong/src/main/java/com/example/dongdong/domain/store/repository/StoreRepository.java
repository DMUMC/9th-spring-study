package com.example.dongdong.domain.store.repository;

import com.example.dongdong.domain.store.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StoreRepository extends JpaRepository<Store, Long> {

    // 특정 평점 이상의 가게 조회
    @Query("SELECT s FROM Store s WHERE s.averageStar >= :minStar")
    List<Store> findByAverageStarGreaterThanEqual(@Param("minStar") Float minStar);
}