package com.example.missionreview.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tbl_mission_info")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class MissionInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tmi_code")
    private Long code;

    @Column(name = "tmi_title")
    private String title;

    @Column(name = "tmi_description", columnDefinition = "TEXT")
    private String description;
}