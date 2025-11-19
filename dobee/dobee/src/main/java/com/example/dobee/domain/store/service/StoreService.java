package com.example.dobee.domain.store.service;

import com.example.dobee.domain.region.entity.Region;
import com.example.dobee.domain.region.repository.RegionRepository;
import com.example.dobee.domain.store.dto.StoreDto;
import com.example.dobee.domain.store.entity.Store;
import com.example.dobee.domain.store.entity.StoreCategory;
import com.example.dobee.domain.store.exception.CategoryNotFoundException;
import com.example.dobee.domain.store.exception.RegionNotFoundException;
import com.example.dobee.domain.store.repository.StoreCategoryRepository;
import com.example.dobee.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreService {

    private final StoreRepository storeRepository;
    private final RegionRepository regionRepository;
    private final StoreCategoryRepository storeCategoryRepository;

    @Transactional
    public StoreDto.StoreResponse addStore(StoreDto.AddStoreRequest addStoreRequest) {
        Region region = regionRepository.findById(addStoreRequest.getRegionId())
                .orElseThrow(RegionNotFoundException::new);

        StoreCategory category = storeCategoryRepository.findById(addStoreRequest.getCategoryId())
                .orElseThrow(CategoryNotFoundException::new);

        Store newStore = addStoreRequest.toEntity(region, category);
        Store savedStore = storeRepository.save(newStore);

        return new StoreDto.StoreResponse(savedStore);
    }
}
