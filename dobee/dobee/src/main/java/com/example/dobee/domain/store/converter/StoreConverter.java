package com.example.dobee.domain.store.converter;

import com.example.dobee.domain.store.dto.StoreDto;
import com.example.dobee.domain.store.entity.Location;
import com.example.dobee.domain.store.entity.Store;

public class StoreConverter {

    public static Store toStore(StoreDto.AddStoreReqDto request, Location location) {
        return Store.builder()
                .name(request.name())
                .detailAddress(request.detailAddress())
                .location(location)
                .build();
    }

    public static StoreDto.AddStoreResDto toAddStoreResDto(Store store) {
        return new StoreDto.AddStoreResDto(
                store.getId(),
                store.getLocation().getName(),
                store.getName(),
                store.getCreatedAt()
        );
    }
}
