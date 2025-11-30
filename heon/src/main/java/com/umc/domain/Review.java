package com.umc.domain;

import com.umc.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Review extends BaseEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;
    
    @Column(nullable = false)
    private Float rating;
    
    @Column(nullable = false, length = 1000)
    private String content;
    
    public void setMember(Member member) {
        if (this.member != null) {
            this.member.getReviews().remove(this);
        }
        this.member = member;
        member.getReviews().add(this);
    }
    
    public void setStore(Store store) {
        if (this.store != null) {
            this.store.getReviews().remove(this);
        }
        this.store = store;
        store.getReviews().add(this);
    }
}
