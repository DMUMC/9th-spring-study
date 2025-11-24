package com.umc.mission.domain.mission.dto;

import com.umc.mission.domain.mission.entity.MemberMission;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;

public class MemberMissionDto {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private Long id;
        private Long memberId;
        private String memberName;
        private Long missionId;
        private String missionTitle;
        private String storeName;
        private String status;
        private LocalDateTime startedAt;
        private LocalDateTime completedAt;
        private LocalDateTime deadlineAt;

        public static Response from(MemberMission memberMission) {
            return Response.builder()
                    .id(memberMission.getId())
                    .memberId(memberMission.getMember().getId())
                    .memberName(memberMission.getMember().getName())
                    .missionId(memberMission.getMission().getId())
                    .missionTitle(memberMission.getMission().getTitle())
                    .storeName(memberMission.getMission().getStore().getName())
                    .status(memberMission.getStatus().name())
                    .startedAt(memberMission.getStartedAt())
                    .completedAt(memberMission.getCompletedAt())
                    .deadlineAt(memberMission.getDeadlineAt())
                    .build();
        }
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PageResponse {
        private List<Response> memberMissions;
        private long totalElements;
        private int numberOfElements;
        private int totalPages;
        private int currentPage;
        private int pageSize;
        private boolean isFirst;
        private boolean isLast;

        public static PageResponse from(Page<MemberMission> page) {
            return PageResponse.builder()
                    .memberMissions(page.getContent().stream()
                            .map(Response::from)
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