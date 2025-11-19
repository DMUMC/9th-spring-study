package com.example.dobee.domain.store.dto;

import com.example.dobee.domain.region.entity.Region;
import com.example.dobee.domain.store.entity.Store;
import com.example.dobee.domain.store.entity.StoreCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

public class StoreDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AddStoreRequest {

        @NotNull(message = "지영 ID는 필수입니다.")
        private Long regionId;

        @NotNull(message = "카테고리 ID는 필수입니다.")
        private Long categoryId;

        @NotBlank(message = "가게 이름은 필수입니다.")
        @Size(max = 50, message = "가게 이름은 50자를 초과할 수 없습니다.")
        private String name;

        @NotBlank(message = "주소는 필수입니다.")
        @Size(max = 200, message = "주소는 200자를 초과할 수 없습니다.")
        private String address;

        @Size(max = 20, message = "전화번호는 20자를 초과할 수 없습니다.")
        private String phoneNum;

        private String description;

        private BigDecimal latitude;

        private BigDecimal longitude;

        private String imageUrl;

        public Store toEntity(Region region, StoreCategory category) {
            return Store.builder()
                    .region(region)
                    .category(category)
                    .name(this.name)
                    .address(this.address)
                    .phoneNum(this.phoneNum)
                    .description(this.description)
                    .latitude(this.latitude)
                    .longitude(this.longitude)
                    .imageUrl(this.imageUrl)
                    .build();
        }
    }

    @Getter
    @AllArgsConstructor
    public static class StoreResponse {
        private Long storeId;
        private String name;
        private String address;

        public StoreResponse(Store store) {
            this.storeId = store.getId();
            this.name = store.getName();
            this.address = store.getAddress();
        }
    }
}
