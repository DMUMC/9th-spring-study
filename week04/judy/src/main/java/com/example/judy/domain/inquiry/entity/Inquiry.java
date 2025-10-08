package com.example.judy.domain.inquiry.entity;

import com.example.judy.domain.inquiry.enums.InquiryType;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "inquiry")
public class Inquiry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", length = 30, nullable = false)
    private String title;

    @Column(name = "content", length = 50, nullable = false)
    private String content;

    @Column(name = "type", length = 10, nullable = false)
    @Enumerated(EnumType.STRING)
    private InquiryType type;

    // 연관 관계 설정
    @OneToMany
    @JoinColumn(name = "inquiry_id", nullable = false)
    private List<InquiryPhoto> inquiryPhotoList = new ArrayList<>();

}
