package com.example.dobee.domain.store.controller;

import com.example.dobee.domain.store.converter.StoreConverter;
import com.example.dobee.domain.store.dto.StoreDto;
import com.example.dobee.domain.store.entity.Store;
import com.example.dobee.domain.store.exception.code.StoreSuccessCode;
import com.example.dobee.domain.store.service.StoreService;
import com.example.dobee.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class StoreController {

    private final StoreService storeService;

    @PostMapping
    public ApiResponse<StoreDto.AddStoreResDto> addStore(@RequestBody @Valid StoreDto.AddStoreReqDto request) {
        Store newStore = storeService.addStore(request);
        return ApiResponse.onSuccess(StoreSuccessCode.STORE_ADD_SUCCESS, StoreConverter.toAddStoreResDto(newStore));
    }
}
