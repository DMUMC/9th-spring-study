package com.example.dobee.domain.mission.repository;

import com.example.dobee.domain.mission.entity.Mission;
import com.example.dobee.domain.store.entity.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionRepository extends JpaRepository<Mission, Long> {
    Page<Mission> findAllByStore(Store store, Pageable pageable);
}
