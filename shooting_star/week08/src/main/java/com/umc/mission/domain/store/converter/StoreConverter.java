package com.umc.mission.domain.store.converter;

import com.umc.mission.domain.region.entity.Region;
import com.umc.mission.domain.store.dto.StoreRequestDTO;
import com.umc.mission.domain.store.dto.StoreResponseDTO;
import com.umc.mission.domain.store.entity.Store;
import com.umc.mission.domain.store.entity.StoreCategory;

public class StoreConverter {
    // dto -> entity
    public static Store toStore(StoreRequestDTO.RequestDTO dto, StoreCategory storeCategory, Region region) {
        return Store.builder()
                .name(dto.getName())
                .address(dto.getAddress())
                .category(storeCategory)
                .region(region)
                .phoneNum(dto.getPhoneNum())
                .imageUrl(dto.getImageUrl())
                .build();
    }

    // entity -> dto
    public static StoreResponseDTO.ResponseDTO toResponseDTO(Store store){
        return StoreResponseDTO.ResponseDTO.builder()
                .storeId(store.getId())
                .name(store.getName())
                .address(store.getAddress())
                .imageUrl(store.getImageUrl())
                .createdAt(store.getCreatedAt())
                .build();
    }
}
