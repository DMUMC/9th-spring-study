package com.example.dobee.domain.mission.dto;

import com.example.dobee.global.annotation.ExistMember;
import com.example.dobee.global.annotation.ExistMission;
import com.example.dobee.global.annotation.ExistStore;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class MissionDto {

    public record AddMissionReqDto(
            @NotNull @ExistStore
            Long storeId,
            @Future
            LocalDate deadline,
            @NotBlank
            String conditional,
            @NotNull @Positive
            Integer point
    ) {}

    public record AddMissionResDto(
            Long missionId,
            String storeName,
            LocalDateTime createdAt
    ) {}

    public record ChallengeMissionReqDto(
            @NotNull @ExistMember
            Long memberId,
            @NotNull @ExistMission
            Long missionId
    ) {}

    public record ChallengeMissionResDto(
            Long memberMissionId,
            String missionStatus,
            LocalDateTime challengedAt
    ) {}

    public record CompleteMissionResDto(
            Long memberMissionId,
            String missionStatus,
            LocalDateTime completedAt
    ) {}

    @Builder
    @Getter
    public static class MissionPreviewDto {
        private Integer reward;
        private String missionSpec;
        private LocalDate dDay;
    }

    @Builder
    @Getter
    public static class MissionPreviewListDto {
        private List<MissionPreviewDto> missionList;
        private Integer listSize;
        private Integer totalPage;
        private Long totalElements;
        private Boolean isFirst;
        private Boolean isLast;
    }

    @Builder
    @Getter
    public static class MyChallengingMissionDto {
        private String storeName;
        private String missionSpec;
        private LocalDate dDay;
        private Integer reward;
    }

    @Builder
    @Getter
    public static class MyChallengingMissionListDto {
        private List<MyChallengingMissionDto> missionList;
        private Integer listSize;
        private Integer totalPage;
        private Long totalElements;
        private Boolean isFirst;
        private Boolean isLast;
    }
}