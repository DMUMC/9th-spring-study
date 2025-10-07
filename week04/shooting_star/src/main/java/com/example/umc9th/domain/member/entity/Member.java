package com.example.umc9th.domain.member.entity;

import com.example.umc9th.domain.member.entity.mapping.MemberFood;
import com.example.umc9th.domain.member.entity.mapping.MemberTerms;
import com.example.umc9th.domain.member.enums.Gender;
import com.example.umc9th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "member")
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 10, nullable = false)
    private String name;

    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    // 초기값 지정
    @Builder.Default
    private Gender gender = Gender.NONE;

    @Column(name = "birthday", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date birthday;

    @Column(name = "address", nullable = false, length = 255)
    private String address;

    @Column(nullable = false,  length = 255)
    private String email;

    @Column(name = "phone_number", nullable = false, length = 20)
    private String phoneNumber;

    @Column(nullable = false)
    private String provider;

    @Column(nullable = false)
    @Builder.Default
    private Integer point = 0;

    @Column(name = "is_active")
    private Boolean isActive;

    // 유저의 약관 정보 및 선호 음식 카테고리는 유저가 삭제되면 함께 삭제되어야 함
    // MemberFood 엔티티의 member 라는 이름의 필드에 의해 매핑됨(1:N 관계에서 1쪽)
    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<MemberFood> memberFoodList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<MemberTerms> memberTermsList = new ArrayList<>();
}
