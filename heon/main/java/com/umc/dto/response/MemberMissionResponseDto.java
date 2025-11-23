package com.umc.dto.response;

import com.umc.domain.MemberMission;
import com.umc.domain.enums.MissionStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@Schema(description = "미션 도전 응답")
public class MemberMissionResponseDto {
    
    @Schema(description = "회원 미션 ID", example = "1")
    private Long memberMissionId;
    
    @Schema(description = "회원 이름", example = "홍길동")
    private String memberName;
    
    @Schema(description = "미션 제목", example = "3회 방문하기")
    private String missionTitle;
    
    @Schema(description = "가게 이름", example = "맛있는 식당")
    private String storeName;
    
    @Schema(description = "리워드 포인트", example = "1000")
    private Integer reward;
    
    @Schema(description = "미션 마감일", example = "2024-12-31")
    private LocalDate deadline;
    
    @Schema(description = "미션 상태", example = "CHALLENGING")
    private MissionStatus status;
    
    @Schema(description = "도전 시작일시", example = "2024-01-15T14:30:00")
    private LocalDateTime createdAt;
    
    public static MemberMissionResponseDto from(MemberMission memberMission) {
        return MemberMissionResponseDto.builder()
                .memberMissionId(memberMission.getId())
                .memberName(memberMission.getMember().getName())
                .missionTitle(memberMission.getMission().getTitle())
                .storeName(memberMission.getMission().getStore().getName())
                .reward(memberMission.getMission().getReward())
                .deadline(memberMission.getMission().getDeadline())
                .status(memberMission.getStatus())
                .createdAt(memberMission.getCreatedAt())
                .build();
    }
}
