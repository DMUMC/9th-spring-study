package com.example.judy.domain.member.entity;

import com.example.judy.domain.member.entity.mapping.MemberFood;
import com.example.judy.domain.member.enums.FoodName;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "food")
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 20, nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private FoodName name = FoodName.NONE;

    // 연관 관계 설정
    @OneToMany
    private List<MemberFood> memberFoodList = new ArrayList<>();

}
