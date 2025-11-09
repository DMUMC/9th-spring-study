package com.example.umc9th.domain.mapping.entity;

import com.example.umc9th.domain.food.entity.Food;
import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.global.entity.BaseEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "user_food")
public class UserFood extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id", nullable=false)
    private Member member;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="food_id", nullable=false)
    private Food food;
}
