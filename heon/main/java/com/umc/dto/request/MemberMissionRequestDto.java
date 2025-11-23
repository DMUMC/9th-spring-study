package com.umc.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Schema(description = "미션 도전 요청")
public class MemberMissionRequestDto {
    
    @Schema(description = "미션 ID", example = "1")
    @NotNull(message = "미션 ID는 필수입니다.")
    private Long missionId;
}
