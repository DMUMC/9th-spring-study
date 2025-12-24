package com.example.umc9th.domain.store.entity;

import com.example.umc9th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "store")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Store extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name", nullable=false, length=50)
    private String name;

    @Column(name="address", nullable=false, length=100)
    private String address;

    @Column(name="detail_address", nullable=false, length=100)
    private String detailAddress;

    @Column(name="manager_name", nullable=false, length=45)
    private String managerName;

    @Column(name="manager_number", nullable=false, length=20)
    private String managerNumber;

    @Column(name="open_time", length=20)
    private String openTime;

    @Column(name="close_time", length=20)
    private String closeTime;

    @Column(name="latitude", nullable=false)
    private Double latitude;

    @Column(name="longitude", nullable=false)
    private Double longitude;

    @Builder
    private Store(
            String name,
            String address,
            String detailAddress,
            String managerName,
            String managerNumber,
            String openTime,
            String closeTime,
            Double latitude,
            Double longitude
    ) {
        this.name = name;
        this.address = address;
        this.detailAddress = detailAddress;
        this.managerName = managerName;
        this.managerNumber = managerNumber;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
