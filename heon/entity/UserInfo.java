package com.example.missionreview.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tbl_user_info")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tui_code")
    private Long code;

    @Column(name = "tui_name")
    private String name;

    @Column(name = "tui_email")
    private String email;
}