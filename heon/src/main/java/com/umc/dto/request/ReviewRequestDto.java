package com.umc.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Schema(description = "리뷰 작성 요청")
public class ReviewRequestDto {
    
    @Schema(description = "가게 ID", example = "1")
    @NotNull(message = "가게 ID는 필수입니다.")
    private Long storeId;
    
    @Schema(description = "평점 (0.0 ~ 5.0)", example = "4.5")
    @NotNull(message = "평점은 필수입니다.")
    @DecimalMin(value = "0.0", message = "평점은 0.0 이상이어야 합니다.")
    @DecimalMax(value = "5.0", message = "평점은 5.0 이하여야 합니다.")
    private Float rating;
    
    @Schema(description = "리뷰 내용", example = "맛있어요!")
    @NotBlank(message = "리뷰 내용은 필수입니다.")
    @Size(min = 5, max = 1000, message = "리뷰는 5자 이상 1000자 이하로 작성해주세요.")
    private String content;
}
