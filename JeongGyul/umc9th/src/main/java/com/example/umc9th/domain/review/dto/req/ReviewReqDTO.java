package com.example.umc9th.domain.review.dto.req;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

public class ReviewReqDTO {

    @Builder
    public record AddDTO(
            @NotNull
            Long memberId,
            @NotNull
            Long storeId,
            @NotBlank
            String content,
            @NotNull
            Float star
    ) {}
}