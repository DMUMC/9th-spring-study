package com.example.dobee.domain.store.dto;

import com.example.dobee.global.annotation.ExistLocation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class StoreDto {

    /**
     * 가게 추가 요청 DTO
     * @param locationId 가게가 속한 지역 ID
     * @param name 가게 이름
     * @param detailAddress 상세 주소
     */
    public record AddStoreReqDto(
            @NotNull @ExistLocation
            Long locationId,
            @NotBlank
            String name,
            @NotBlank
            String detailAddress
    ) {}

    /**
     * 가게 추가 응답 DTO
     * @param storeId 생성된 가게 ID
     * @param locationName 가게가 속한 지역 이름
     * @param storeName 생성된 가게 이름
     * @param createdAt 가게 생성 일시
     */
    public record AddStoreResDto(
            Long storeId,
            String locationName,
            String storeName,
            LocalDateTime createdAt
    ) {}
}
