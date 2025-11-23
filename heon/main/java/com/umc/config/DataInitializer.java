package com.umc.config;

import com.umc.domain.Member;
import com.umc.domain.Mission;
import com.umc.domain.Store;
import com.umc.repository.MemberRepository;
import com.umc.repository.MissionRepository;
import com.umc.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;
    private final MissionRepository missionRepository;
    
    @Override
    public void run(String... args) {
        // 테스트용 회원 데이터 (ID: 1)
        if (memberRepository.count() == 0) {
            Member member = Member.builder()
                    .name("홍길동")
                    .email("hong@example.com")
                    .phoneNumber("010-1234-5678")
                    .build();
            memberRepository.save(member);
            System.out.println("테스트 회원 생성 완료: ID = " + member.getId());
        }
        
        // 테스트용 가게 데이터
        if (storeRepository.count() == 0) {
            Store store1 = Store.builder()
                    .name("맛있는 식당")
                    .address("서울시 강남구 테헤란로 123")
                    .description("정말 맛있는 식당입니다!")
                    .rating(4.5f)
                    .build();
            
            Store store2 = Store.builder()
                    .name("행복한 카페")
                    .address("서울시 서초구 반포대로 456")
                    .description("분위기 좋은 카페입니다!")
                    .rating(4.8f)
                    .build();
            
            storeRepository.save(store1);
            storeRepository.save(store2);
            System.out.println("테스트 가게 생성 완료");
            
            // 테스트용 미션 데이터
            Mission mission1 = Mission.builder()
                    .title("3회 방문하기")
                    .description("맛있는 식당에 3회 방문하면 포인트 적립!")
                    .reward(1000)
                    .deadline(LocalDate.now().plusMonths(1))
                    .build();
            mission1.setStore(store1);
            
            Mission mission2 = Mission.builder()
                    .title("5만원 이상 주문하기")
                    .description("5만원 이상 주문하면 포인트 적립!")
                    .reward(2000)
                    .deadline(LocalDate.now().plusMonths(2))
                    .build();
            mission2.setStore(store1);
            
            Mission mission3 = Mission.builder()
                    .title("커피 10잔 구매하기")
                    .description("커피 10잔 구매하면 1잔 무료!")
                    .reward(5000)
                    .deadline(LocalDate.now().plusMonths(3))
                    .build();
            mission3.setStore(store2);
            
            missionRepository.save(mission1);
            missionRepository.save(mission2);
            missionRepository.save(mission3);
            System.out.println("테스트 미션 생성 완료");
        }
    }
}
