package com.example.judy.domain.member.entity;

import com.example.judy.domain.alarm.entity.mapping.MemberAlarm;
import com.example.judy.domain.inquiry.entity.Inquiry;
import com.example.judy.domain.member.entity.mapping.MemberFood;
import com.example.judy.domain.member.entity.mapping.MemberTerm;
import com.example.judy.domain.member.enums.Gender;
import com.example.judy.domain.member.enums.MemberStatus;
import com.example.judy.domain.mission.entity.mapping.MemberMission;
import com.example.judy.domain.review.entity.Review;
import com.example.judy.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
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

    @Column(name = "profile_image_url")
    private String profileImageUrl;

    @Column(name = "name", length = 10, nullable = false)
    private String name;

    @Column(name = "gender", length = 10, nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Column(name = "address", length = 50, nullable = false)
    private String address;

    @Column(name = "email", length = 50)
    private String email;

    @Column(name = "address_detail", length = 30, nullable = false)
    private String addressDetail;

    @Column(name = "nickname", length = 30)
    private String nickname;

    @Column(name = "phone_num", length = 13)
    private String phoneNum;

    @Column(name = "point", nullable = false)
    @Builder.Default
    private Integer point = 0;

    @Column(name = "status", length = 10, nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private MemberStatus status = MemberStatus.ACTIVE;

    @Column(name = "inactive_at")
    private LocalDateTime inactiveAt;

    // 연관 관계 설정
    @OneToMany(cascade = CascadeType.REMOVE)
    private List<MemberFood> memberFoodList = new ArrayList<>();

    // 연관 관계 설정
    @OneToMany(cascade = CascadeType.REMOVE)
    private List<MemberTerm> memberTermList = new ArrayList<>();

    // 연관 관계 설정
    @OneToMany(cascade = CascadeType.REMOVE)
    private List<MemberMission> memberMissionList = new ArrayList<>();

    // 연관 관계 설정
    @OneToMany(cascade = CascadeType.REMOVE)
    private List<MemberAlarm> memberAlarmList = new ArrayList<>();

    // 연관 관계 설정
    @OneToMany
    @JoinColumn(name = "member_id", nullable = false)
    private List<Review> reviewList = new ArrayList<>();

    // 연관 관계 설정
    @OneToMany
    @JoinColumn(name = "member_id", nullable = false)
    private List<Inquiry> inquiryList = new ArrayList<>();

    // 연관 관계 설정
    @OneToMany
    @JoinColumn(name = "member_id", nullable = false)
    private List<Social> socialList = new ArrayList<>();

}