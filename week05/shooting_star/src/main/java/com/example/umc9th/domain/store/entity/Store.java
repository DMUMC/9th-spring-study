package com.example.umc9th.domain.store.entity;

import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "store")
public class Store extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 45)
    private String name;

    @Column(name = "store_category", nullable = false, length = 45)
    private String storeCategory;

    @Column(name = "store_address", nullable = false)
    private String storeAddress;

    @Column(name = "separation_number")
    private Integer separationNumber;

    // 가게가 사라지면 그 가게의 미션도 사라져야 함
    @OneToMany(mappedBy = "store", cascade = CascadeType.REMOVE)
    private List<Mission> missionList = new ArrayList<>();
}
