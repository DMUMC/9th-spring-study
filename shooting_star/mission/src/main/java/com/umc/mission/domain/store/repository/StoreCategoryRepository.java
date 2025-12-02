package com.umc.mission.domain.store.repository;

import com.umc.mission.domain.store.entity.StoreCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreCategoryRepository extends JpaRepository<StoreCategory, Long> {

}
