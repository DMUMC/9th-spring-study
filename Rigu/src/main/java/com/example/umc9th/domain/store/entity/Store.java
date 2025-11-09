package com.example.umc9th.domain.store.entity;

import com.example.umc9th.global.entity.BaseEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "store")
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
}
