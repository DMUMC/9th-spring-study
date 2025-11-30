package com.umc.dto.response;

import com.umc.domain.MemberMission;
import com.umc.domain.enums.MissionStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SimpleMemberMissionResponseDto {
    
    private Long id;
    private Long memberId;
    private String memberNickname;
    private Long missionId;
    private String missionTitle;
    private String storeName;
    private Integer reward;
    private LocalDate deadline;
    private MissionStatus status;
    private LocalDateTime createdAt;
    
    public static SimpleMemberMissionResponseDto from(MemberMission memberMission) {
        return SimpleMemberMissionResponseDto.builder()
                .id(memberMission.getId())
                .memberId(memberMission.getMember().getId())
                .memberNickname(memberMission.getMember().getNickname())
                .missionId(memberMission.getMission().getId())
                .missionTitle(memberMission.getMission().getTitle())
                .storeName(memberMission.getMission().getStore().getName())
                .reward(memberMission.getMission().getReward())
                .deadline(memberMission.getMission().getDeadline())
                .status(memberMission.getStatus())
                .createdAt(memberMission.getCreatedAt())
                .build();
    }
}
