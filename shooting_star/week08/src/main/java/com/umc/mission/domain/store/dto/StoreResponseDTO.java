package com.umc.mission.domain.store.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class StoreResponseDTO {
    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ResponseDTO {
        private Long storeId;
        private String name;
        private String address;
        private String imageUrl;
        private LocalDateTime createdAt;
    }
}
