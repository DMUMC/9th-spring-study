package com.example.missionreview.repository;

import com.example.missionreview.entity.ReviewInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewInfoRepo extends JpaRepository<ReviewInfo, Long> {
}