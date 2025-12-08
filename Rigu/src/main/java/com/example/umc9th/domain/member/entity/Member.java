package com.example.umc9th.domain.member.entity;

import com.example.umc9th.global.common.enums.Gender;
import com.example.umc9th.global.common.enums.SocialType;
import com.example.umc9th.global.entity.BaseEntity;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(
        name = "member",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_member_email",
                        columnNames = "email"
                )
        }
)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name", nullable=false, length=45)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name="gender", nullable=false, length=10)
    private Gender gender = Gender.NONE;

    @Column(name="birth", nullable=false)
    private LocalDate birth;

    @Column(name="address", nullable=false, length=100)
    private String address;

    @Column(name="detail_address", nullable=false, length=100)
    private String detailAddress;

    @Column(name="email", nullable=false, length=100)
    private String email;

    // 🔐 로그인용 패스워드(BCrypt 인코딩 값)
    @Column(name="password", nullable=false, length=255)
    private String password;

    @Column(name="nickname", nullable=false, length=45)
    private String nickname;

    @Column(name="phone_number", nullable=false, length=20)
    private String phoneNumber;

    @Column(name="point", nullable=false)
    private Integer point = 0;

    @Column(name="social_uid", nullable=false, length=100)
    private String socialUid;

    @Enumerated(EnumType.STRING)
    @Column(name="social_type", nullable=false, length=20)
    private SocialType socialType = SocialType.KAKAO;

    @Column(name="inactive_date")
    private LocalDate inactiveDate;

    @Column(name="deleted_at")
    private LocalDateTime deletedAt;
}
