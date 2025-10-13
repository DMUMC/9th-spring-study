package com.example.umc9th.domain.mission.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

import java.time.LocalDateTime;

// Getter, Setter 등을 자동 생성
@Data
public class AvailableMissionDTO {
    private Long missionId;
    private Integer point;
    private LocalDateTime deadline;
    private String storeName;
    private String storeCategory;

    // QueryDSL에서 dto로 바로 조회하기 위함
    // Gradle을 재빌드하게 되면 QAvailableMissionDTO 라는 클래스가 생성되어 QueryDSL에서 타입을 안전하게 사용 가능
    @QueryProjection
    public AvailableMissionDTO(Long missionId, Integer point, LocalDateTime deadline, String storeName, String storeCategory) {
        this.missionId = missionId;
        this.point = point;
        this.deadline = deadline;
        this.storeName = storeName;
        this.storeCategory = storeCategory;
    }
}
