package com.example.dobee.domain.member.entity;

import com.example.dobee.domain.member.entity.mapping.MemberFood;
import com.example.dobee.domain.member.entity.mapping.MemberTerm;
import com.example.dobee.domain.member.enums.Gender;
import com.example.dobee.domain.store.enums.Address;
import com.example.dobee.domain.member.enums.SocialType;
import com.example.dobee.global.auth.enums.Role;
import com.example.dobee.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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
@EntityListeners(AuditingEntityListener.class)
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "name", nullable = false) // NOT NULL
    private String name;

    @Column(nullable = false, unique = true)
    private String nickname;

    @Column(name = "gender", nullable = false) // NOT NULL
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Gender gender = Gender.NONE; // Default value: NONE

    @Column(name = "birth", nullable = false) // NOT NULL
    private LocalDate birth;

    @Column(name = "address", nullable = false) // NOT NULL
    @Enumerated(EnumType.STRING)
    private Address address;

    @Column(name = "detail_address", nullable = false) // NOT NULL
    private String detailAddress;

    @Column(name = "social_uid") // NULL 허용
    private String socialUid;

    @Column(name = "social_type") // NULL 허용
    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    @Column(name = "point", nullable = false) // NOT NULL
    @Builder.Default
    private int point = 0; // Default value: 0 (primitive type ensures not null implicitly, but explicit init is safer with Builder)

    @Column(name = "phone_number") // NULL 허용 (nullable = true가 기본값)
    private String phoneNumber;

    @Column(name = "deleted_at") // NULL 허용, 명세서에 추가된 필드
    private LocalDateTime deletedAt;

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<MemberFood> memberFoodList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<MemberTerm> memberTermList = new ArrayList<>();


}
