package com.example.missionreview.repository;

import com.example.missionreview.entity.PhotoInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoInfoRepo extends JpaRepository<PhotoInfo, Long> {
}