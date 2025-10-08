package com.example.judy.domain.member.entity;

import com.example.judy.domain.member.entity.mapping.MemberTerm;
import com.example.judy.domain.member.enums.TermType;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "term")
public class Term {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type", length = 10, nullable = false)
    private TermType type;

    // 연관 관계 설정
    @OneToMany
    private List<MemberTerm> memberTermList = new ArrayList<>();

}
