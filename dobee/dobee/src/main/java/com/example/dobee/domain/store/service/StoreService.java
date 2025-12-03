package com.example.dobee.domain.store.service;

import com.example.dobee.domain.store.converter.StoreConverter;
import com.example.dobee.domain.store.dto.StoreDto;
import com.example.dobee.domain.store.entity.Location;
import com.example.dobee.domain.store.entity.Store;
import com.example.dobee.domain.store.exception.StoreException;
import com.example.dobee.domain.store.exception.code.StoreErrorCode;
import com.example.dobee.domain.store.repository.LocationRepository;
import com.example.dobee.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class StoreService {

    private final StoreRepository storeRepository;
    private final LocationRepository locationRepository;

    public Store addStore(StoreDto.AddStoreReqDto request) {
        Location location = locationRepository.findById(request.locationId())
                .orElseThrow(() -> new StoreException(StoreErrorCode.LOCATION_NOT_FOUND));

        Store newStore = StoreConverter.toStore(request, location);

        return storeRepository.save(newStore);
    }
}
