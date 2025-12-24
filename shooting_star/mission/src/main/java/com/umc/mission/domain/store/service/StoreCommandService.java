package com.umc.mission.domain.store.service;

import com.umc.mission.domain.region.entity.Region;
import com.umc.mission.domain.store.dto.StoreRequestDTO;
import com.umc.mission.domain.store.entity.Store;
import com.umc.mission.domain.store.entity.StoreCategory;

public interface StoreCommandService {
    Store addStore(Long regionId, Long storeCategoryId, StoreRequestDTO.RequestDTO requestDTO);
}
