package com.umc.mission.domain.store.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class StoreRequestDto {
    @NotNull
    private Long regionId;
    @NotNull
    private Long storeCategoryId;
    @NotBlank
    private String name;
    @NotBlank
    private String address;
    private String imageUrl;
}