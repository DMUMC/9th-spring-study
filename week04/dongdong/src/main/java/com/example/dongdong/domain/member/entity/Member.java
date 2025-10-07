package com.example.dongdong.domain.member.entity;

import com.example.dongdong.domain.memberFood.entity.MemberFood;
import com.example.dongdong.domain.missionMember.entity.MissionMember;
import com.example.dongdong.domain.notificationMember.entity.NotificationMember;
import com.example.dongdong.domain.qna.entity.Qna;
import com.example.dongdong.domain.review.entity.Review;
import com.example.dongdong.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String gender; // Enum 타입으로 관리하는 것을 추천합니다.
    private LocalDate birth;
    private String address;
    private String addressDetail;
    private String phoneNumber;

    // 소셜 로그인 정보
    private String provider;

    private LocalDate joinDate;
    private Integer havePoint;

    // 알림 설정
    private Boolean eventNotification;
    private Boolean storeNotification;
    private Boolean qnaNotification;

    // 연관 관계
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Review> reviewList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Qna> qnaList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberFood> memberFoodList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<NotificationMember> notificationMemberList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MissionMember> missionMemberList = new ArrayList<>();
}