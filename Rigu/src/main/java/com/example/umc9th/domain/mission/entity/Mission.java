package com.example.umc9th.domain.mission.entity;

import com.example.umc9th.domain.store.entity.Store;
import com.example.umc9th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "mission")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Mission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="content", nullable=false, length=100)
    private String content;

    @Column(name="point", nullable=false)
    private Integer point;

    @Column(name="deadline", nullable=false)
    private LocalDateTime deadline;

    @ManyToOne(fetch=FetchType.LAZY, optional=false)
    @JoinColumn(name="store_id", nullable=false)
    private Store store;
}
