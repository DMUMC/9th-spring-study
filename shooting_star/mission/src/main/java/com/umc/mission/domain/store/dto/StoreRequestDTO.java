package com.umc.mission.domain.store.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class StoreRequestDTO {
    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RequestDTO{
        @NotBlank
        private String name;
        @NotBlank(message = "가게 주소")
        private String address;
        @NotNull(message = "가게 카테고리 id는 필수입니다.")
        private Long categoryId;
        @NotNull(message = "지역 id는 필수입니다.")
        private Long regionId;
        @NotNull(message = "가게 전화번호는 필수입니다.")
        private String phoneNum;
        private String imageUrl;
    }
}
