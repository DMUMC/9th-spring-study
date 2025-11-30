package com.umc.domain;

import com.umc.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Mission extends BaseEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;
    
    @Column(nullable = false, length = 100)
    private String title;
    
    @Column(length = 500)
    private String description;
    
    @Column(nullable = false)
    private Integer reward;
    
    @Column(nullable = false)
    private LocalDate deadline;
    
    @OneToMany(mappedBy = "mission", cascade = CascadeType.ALL)
    @Builder.Default
    private List<MemberMission> memberMissions = new ArrayList<>();
    
    public void setStore(Store store) {
        if (this.store != null) {
            this.store.getMissions().remove(this);
        }
        this.store = store;
        store.getMissions().add(this);
    }
}
