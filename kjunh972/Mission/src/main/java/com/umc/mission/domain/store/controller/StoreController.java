package com.umc.mission.domain.store.controller;

import com.umc.mission.domain.store.dto.StoreSearchDto;
import com.umc.mission.domain.store.service.StoreService;
import com.umc.mission.global.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stores")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    /**
     * 가게 검색 API (지역 필터, 이름 검색, 정렬, 페이징)
     */
    @GetMapping("/search")
    public ApiResponse<StoreSearchDto.SearchPageResponse> searchStores(
            @RequestParam(required = false) Long regionId,
            @RequestParam(required = false) List<Long> regionIds,
            @RequestParam(required = false) String searchKeyword,
            @RequestParam(defaultValue = "latest") String sortBy,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {

        StoreSearchDto.SearchPageResponse response = storeService.searchStores(
                regionId, regionIds, searchKeyword, sortBy, page, size);

        return ApiResponse.ok(response);
    }

    /**
     * 가게 상세 조회
     */
    @GetMapping("/{storeId}")
    public ApiResponse<StoreSearchDto.StoreResponse> getStore(@PathVariable Long storeId) {
        StoreSearchDto.StoreResponse response = storeService.getStoreDetail(storeId);
        return ApiResponse.ok(response);
    }
}