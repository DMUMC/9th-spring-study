package com.example.missionreview.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tbl_address_info")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class AddressInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tadi_code")
    private Long code;

    @Column(name = "tadi_name")
    private String name;
}