package com.umc.mission.domain.store.service;

import com.umc.mission.domain.region.entity.Region;
import com.umc.mission.domain.region.repository.RegionRepository;
import com.umc.mission.domain.store.dto.StoreRequestDto;
import com.umc.mission.domain.store.dto.StoreSearchDto;
import com.umc.mission.domain.store.entity.Store;
import com.umc.mission.domain.store.entity.StoreCategory;
import com.umc.mission.domain.store.repository.StoreCategoryRepository;
import com.umc.mission.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;
    private final RegionRepository regionRepository;
    private final StoreCategoryRepository storeCategoryRepository;


    @Transactional
    public Long createStore(StoreRequestDto request) {
        Region region = regionRepository.findById(request.getRegionId())
                .orElseThrow(() -> new IllegalArgumentException("Region not found"));

        StoreCategory category = storeCategoryRepository.findById(request.getStoreCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));

        Store store = Store.builder()
                .region(region)
                .category(category)
                .name(request.getName())
                .address(request.getAddress())
                .imageUrl(request.getImageUrl())
                .build();

        return storeRepository.save(store).getId();
    }
    /**
     * 가게 검색 (지역 필터, 이름 검색, 정렬, 페이징)
     */
    @Transactional(readOnly = true)
    public StoreSearchDto.SearchPageResponse searchStores(Long regionId, List<Long> regionIds,
                                                         String searchKeyword, String sortBy,
                                                         int page, int size) {
        int validPage = Math.max(0, page);
        int validSize = size > 0 ? size : 20;
        String validSortBy = sortBy != null ? sortBy : "latest";
        String normalizedKeyword = normalizeSearchKeyword(searchKeyword);

        StoreSearchDto.SearchRequest request = StoreSearchDto.SearchRequest.builder()
                .regionId(regionId)
                .regionIds(regionIds)
                .searchKeyword(normalizedKeyword)
                .sortBy(validSortBy)
                .page(validPage)
                .size(validSize)
                .build();

        Page<Store> searchResults = storeRepository.searchStores(
                request.getRegionId(),
                request.getRegionIds(),
                request.getSearchKeyword(),
                request.getSortBy(),
                PageRequest.of(request.getPage(), request.getSize())
        );

        return StoreSearchDto.SearchPageResponse.from(searchResults);
    }

    @Transactional(readOnly = true)
    public StoreSearchDto.StoreResponse getStoreDetail(Long storeId) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new IllegalArgumentException("가게를 찾을 수 없습니다. ID: " + storeId));

        return StoreSearchDto.StoreResponse.from(store);
    }

    /**
     * 검색어 정규화 및 유효성 검사
     * 연속된 공백을 단일 공백으로 변환하고 앞뒤 공백 제거
     */
    public String normalizeSearchKeyword(String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            return null;
        }

        return keyword.trim().replaceAll("\\s+", " ");
    }

    /**
     * 검색어를 공백으로 분리하여 배열로 반환
     */
    public String[] splitSearchKeyword(String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            return new String[0];
        }

        return keyword.trim().split("\\s+");
    }
}