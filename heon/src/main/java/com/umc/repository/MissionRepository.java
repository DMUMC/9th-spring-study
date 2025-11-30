package com.umc.repository;

import com.umc.domain.Mission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MissionRepository extends JpaRepository<Mission, Long> {
    List<Mission> findByStoreId(Long storeId);
    
    Page<Mission> findByStoreId(Long storeId, Pageable pageable);
}
