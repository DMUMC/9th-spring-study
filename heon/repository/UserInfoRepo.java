package com.example.missionreview.repository;

import com.example.missionreview.entity.UserCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCodeRepo extends JpaRepository<UserInfo, Long> {
}