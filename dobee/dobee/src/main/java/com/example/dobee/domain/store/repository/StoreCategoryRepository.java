package com.example.dobee.domain.store.repository;

import com.example.dobee.domain.store.entity.StoreCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreCategoryRepository extends JpaRepository<StoreCategory,Long> {
}
