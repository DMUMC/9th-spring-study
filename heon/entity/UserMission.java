package com.example.missionreview.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tbl_user_mission")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class UserMission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tum_code")
    private Long code;

    @Column(name = "tum_tuicode")
    private Long userCode;

    @Column(name = "tum_tmicode")
    private Long missionCode;

    @Column(name = "tum_status")
    private String status; // 진행중, 완료 등
}