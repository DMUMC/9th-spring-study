package com.example.dobee.domain.store.controller;

import com.example.dobee.domain.store.dto.StoreDto;
import com.example.dobee.domain.store.service.StoreService;
import com.example.dobee.global.code.SuccessCode;
import com.example.dobee.global.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
@Tag(name = "Store", description = "가게 관련 API")
public class StoreController {

    private final StoreService storeService;

    @PostMapping
    @Operation(summary = "특정 지역에 가게 추가 API", description = "특정 지역에 새로운 가게를 추가 합니다.")
    public ApiResponse<StoreDto.StoreResponse> addStore(@Valid @RequestBody StoreDto.AddStoreRequest request) {
        StoreDto.StoreResponse response = storeService.addStore(request);

        return ApiResponse.onSuccess(SuccessCode.CREATED, response);
    }
}
