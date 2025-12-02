package com.umc.mission.domain.store.controller;

import com.umc.mission.domain.store.converter.StoreConverter;
import com.umc.mission.domain.store.dto.StoreRequestDTO;
import com.umc.mission.domain.store.dto.StoreResponseDTO;
import com.umc.mission.domain.store.entity.Store;
import com.umc.mission.domain.store.exception.StoreSuccessCode;
import com.umc.mission.domain.store.service.StoreCommandService;
import com.umc.mission.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/store")
@RequiredArgsConstructor
public class StoreController {
    private final StoreCommandService storeService;

    @PostMapping("/region/{regionId}")
    public ApiResponse<StoreResponseDTO.ResponseDTO> createStore(
            @PathVariable(name = "regionId") Long regionId,
            @RequestBody @Valid StoreRequestDTO.RequestDTO requestDTO
            ){
        Store store = storeService.addStore(regionId, requestDTO.getCategoryId(), requestDTO);
        StoreResponseDTO.ResponseDTO responseDTO = StoreConverter.toResponseDTO(store);

        return ApiResponse.onSuccess(
                StoreSuccessCode.OK,
                responseDTO
        );
    }
}
