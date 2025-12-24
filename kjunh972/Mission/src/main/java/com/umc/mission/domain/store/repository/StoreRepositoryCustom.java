package com.umc.mission.domain.store.repository;

import com.umc.mission.domain.store.entity.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StoreRepositoryCustom {

    /**
     * 지역 필터, 이름 검색, 정렬을 지원하는 복합 검색 조회
     */
    Page<Store> searchStores(Long regionId, List<Long> regionIds, String searchKeyword,
                            String sortBy, Pageable pageable);

    /**
     * 공백 포함 검색어 처리
     */
    Page<Store> searchStoresWithMultipleKeywords(String[] keywords, Long regionId,
                                                List<Long> regionIds, String sortBy,
                                                Pageable pageable);

    /**
     * 공백 없는 검색어 처리
     */
    Page<Store> searchStoresWithSingleKeyword(String keyword, Long regionId,
                                             List<Long> regionIds, String sortBy,
                                             Pageable pageable);
}