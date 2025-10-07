package com.example.umc9th.domain.member.entity.mapping;

import com.example.umc9th.domain.member.entity.FoodCategory;
import com.example.umc9th.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "member_food_preference")
public class MemberFood {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*
    * FetchType.LAZY: 지연로딩
    * FetchType은 연관된 엔티티를 언제 데이터베이스에서 조회할지 결정하는 옵션
    * LAZY와 EAGER 종류가 있음
    * LAZY(게으른): ex) Member 엔티티를 조회할 때, 연관된 MemberFood 엔티티는 일단 무시하고 가져오지 않음
    * 개발자가 코드에서 실제로 member.getMemberFood() 같이 호출하여 MemberFood 객체를 사용하려고 하는 시점에
    * jpa가 DB에 MemberFood 엔티티를 조회하는 쿼리를 날림.
    * EAGER(부지런): ex) Member 엔티티를 조회할 때, 무조건 연관된 엔티티까지 함께 DB에서 조회(join)함.
    * 연관된 테이블의 데이터가 필요 없는 상황에서도 항상 함께 조회하므로, 불필요한 join 발생!
    * 특히 @OneToMany 관계에서는 N+1 문제와 같은 성능 문제를 야기할 수 있음
    * 성능 최적화를 위해 FetchType.LAZY로 설정하는 습관 들이기! (기본값은 EAGER)
    */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    // referencedColumnName 속성(인수)을 생략하면 기본적으로 상대방 테이블의 pk 칼럼을 참조함
    @JoinColumn(name = "food_category_id")
    private FoodCategory foodCategory;
}
