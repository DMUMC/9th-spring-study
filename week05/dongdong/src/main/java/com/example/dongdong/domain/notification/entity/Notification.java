package com.example.dongdong.domain.notification.entity;

import com.example.dongdong.domain.notificationMember.entity.NotificationMember;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title; // "이벤트 or 리뷰 or 문의답변"

    @Column(columnDefinition = "TEXT")
    private String body;

    // 연관 관계
    @OneToMany(mappedBy = "notification", cascade = CascadeType.ALL)
    private List<NotificationMember> notificationMemberList = new ArrayList<>();
}