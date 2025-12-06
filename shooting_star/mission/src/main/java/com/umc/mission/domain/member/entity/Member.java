package com.umc.mission.domain.member.entity;

import com.umc.mission.domain.member.enums.FoodType;
import com.umc.mission.domain.member.enums.MemberStatus;
import com.umc.mission.domain.mission.entity.MemberMission;
import com.umc.mission.domain.point.entity.PointHistory;
import com.umc.mission.domain.region.entity.RegionMissionStats;
import com.umc.mission.domain.review.entity.Review;
import com.umc.mission.global.auth.enums.Role;
import com.umc.mission.global.entity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "member")
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Email
    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @NotBlank
    @Size(max = 20)
    @Column(nullable = false, length = 20)
    private String name;

    @Size(max = 20)
    @Column(length = 20)
    private String nickname;

    @Column(name = "phone_num", length = 20)
    private String phoneNum;

    @Column(length = 10)
    private String gender;

    @Column(name = "profile_image", length = 255)
    private String profileImage;

    @NotBlank
    @Column(name = "social_type", nullable = false, length = 20)
    private String socialType;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 15)
    @Builder.Default
    private MemberStatus status = MemberStatus.ACTIVE;

    @Column(name = "inactive_date", columnDefinition = "DATETIME(6)")
    private LocalDateTime inactiveDate;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private Set<MemberMission> memberMissions = new HashSet<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private Set<Review> reviews = new HashSet<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    @Builder.Default
    private Set<PointHistory> pointHistories = new HashSet<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private Set<RegionMissionStats> regionMissionStats = new HashSet<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<MemberPreferredFood> memberPreferredFoodList = new ArrayList<>();

    public void addPreferredFood(FoodType food) {
        MemberPreferredFood preferredFood = MemberPreferredFood.builder()
                .foodType(food).build();

        this.memberPreferredFoodList.add(preferredFood);

        preferredFood.setMember(this);
    }
}