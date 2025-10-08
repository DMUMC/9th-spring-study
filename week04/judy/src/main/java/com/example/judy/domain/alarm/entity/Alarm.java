package com.example.judy.domain.alarm.entity;

import com.example.judy.domain.alarm.entity.mapping.MemberAlarm;
import com.example.judy.domain.alarm.enums.AlarmType;
import com.example.judy.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "alarm")
public class Alarm extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type", length = 10, nullable = false)
    private AlarmType type;

    @Column(name = "content", length = 50, nullable = false)
    private String content;

    // 연관 관계 설정
    @OneToMany
    private List<MemberAlarm> memberAlarmList = new ArrayList<>();


}
