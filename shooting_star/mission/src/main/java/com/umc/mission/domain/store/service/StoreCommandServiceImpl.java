package com.umc.mission.domain.store.service;

import com.umc.mission.domain.region.entity.Region;
import com.umc.mission.domain.region.exception.RegionErrorCode;
import com.umc.mission.domain.region.exception.RegionException;
import com.umc.mission.domain.region.repository.RegionRepository;
import com.umc.mission.domain.store.converter.StoreConverter;
import com.umc.mission.domain.store.dto.StoreRequestDTO;
import com.umc.mission.domain.store.entity.Store;
import com.umc.mission.domain.store.entity.StoreCategory;
import com.umc.mission.domain.store.exception.StoreCategoryErrorCode;
import com.umc.mission.domain.store.exception.StoreHandler;
import com.umc.mission.domain.store.repository.StoreCategoryRepository;
import com.umc.mission.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreCommandServiceImpl implements StoreCommandService {
    private final RegionRepository regionRepository;
    private final StoreCategoryRepository storeCategoryRepository;
    private final StoreRepository storeRepository;

    @Override
    public Store addStore(
            Long regionId,
            Long storeCategoryId,
            StoreRequestDTO.RequestDTO requestDTO
    ){
        // 지역 조회
        Region region = regionRepository.findById(regionId)
                .orElseThrow(() -> new RegionException(RegionErrorCode.NOT_FOUND));

        // 가게 카테고리 조회
        StoreCategory storeCategory = storeCategoryRepository.findById(storeCategoryId)
                .orElseThrow(() -> new StoreHandler(StoreCategoryErrorCode.NOT_FOUND));

        // 가게 정보 만들기
        Store store = StoreConverter.toStore(requestDTO, storeCategory, region);

        return storeRepository.save(store);
    }
}
