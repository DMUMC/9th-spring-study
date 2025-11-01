package com.example.missionreview.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_photo_info")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class PhotoInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tpi_code")
    private Long tpiCode;

    @Column(name = "tpi_targetname")
    private String targetName; // ex: tbl_review_info

    @Column(name = "tpi_targetcode")
    private Long targetCode; // autoTriCode

    @Lob
    @Column(name = "tpi_binary")
    private byte[] binary;

    @Column(name = "tpi_file_name")
    private String fileName;

    @Column(name = "tpi_regidate")
    private LocalDateTime regidate = LocalDateTime.now();
}