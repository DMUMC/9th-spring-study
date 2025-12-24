package com.umc.mission.domain.store.dto;

import com.umc.mission.domain.store.entity.Store;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

public class StoreSearchDto {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SearchRequest {
        private Long regionId;
        private List<Long> regionIds;
        private String searchKeyword;
        private String sortBy;
        private int page;
        private int size;
        private Long cursorId;
        private String cursorName;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class StoreResponse {
        private Long id;
        private String name;
        private String regionName;
        private String categoryName;
        private String address;
        private String phoneNum;
        private String description;
        private BigDecimal rating;
        private Integer reviewCount;
        private String imageUrl;

        public static StoreResponse from(Store store) {
            return StoreResponse.builder()
                    .id(store.getId())
                    .name(store.getName())
                    .regionName(store.getRegion().getName())
                    .categoryName(store.getCategory().getName())
                    .address(store.getAddress())
                    .phoneNum(store.getPhoneNum())
                    .description(store.getDescription())
                    .rating(store.getRating())
                    .reviewCount(store.getReviewCount())
                    .imageUrl(store.getImageUrl())
                    .build();
        }
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SearchPageResponse {
        private List<StoreResponse> stores;
        private long totalElements;
        private int numberOfElements;
        private int totalPages;
        private int currentPage;
        private int pageSize;
        private boolean isFirst;
        private boolean isLast;

        public static SearchPageResponse from(org.springframework.data.domain.Page<Store> page) {
            return SearchPageResponse.builder()
                    .stores(page.getContent().stream()
                            .map(StoreResponse::from)
                            .toList())
                    .totalElements(page.getTotalElements())
                    .numberOfElements(page.getNumberOfElements())
                    .totalPages(page.getTotalPages())
                    .currentPage(page.getNumber())
                    .pageSize(page.getSize())
                    .isFirst(page.isFirst())
                    .isLast(page.isLast())
                    .build();
        }
    }
}