package com.example.dobee.domain.member.entity;

import com.example.dobee.domain.member.entity.mapping.MemberTerm;
import com.example.dobee.domain.member.enums.TermType;
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

    @Column(name = "name")
    private TermType name;

    @OneToMany(mappedBy = "term")
    private List<MemberTerm> memberTermList = new ArrayList<>();
}
