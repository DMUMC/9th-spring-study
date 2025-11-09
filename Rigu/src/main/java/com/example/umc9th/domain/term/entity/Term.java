package com.example.umc9th.domain.term.entity;

import com.example.umc9th.global.entity.BaseEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "term")
public class Term extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name", nullable=false, length=50)
    private String name;

    @Column(name="content", nullable=false, columnDefinition="TEXT")
    private String content;

    @Column(name="is_required", nullable=false)
    private boolean isRequired;
}
