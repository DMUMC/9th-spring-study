package com.example.umc9th.domain.food.entity;

import com.example.umc9th.global.entity.BaseEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "food")
public class Food extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name", nullable=false, length=50)
    private String name;
}
