package com.umc.config;

import com.umc.domain.*;
import com.umc.domain.enums.MissionStatus;
import com.umc.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;
    private final MissionRepository missionRepository;
    private final ReviewRepository reviewRepository;
    private final MemberMissionRepository memberMissionRepository;
    
    @Override
    @Transactional
    public void run(String... args) {
        // 테스트용 회원 데이터 (ID: 1)
        Member member = null;
        if (memberRepository.count() == 0) {
            member = Member.builder()
                    .name("홍길동")
                    .nickname("닉네임1234")
                    .email("hong@example.com")
                    .phoneNumber("010-1234-5678")
                    .build();
            memberRepository.save(member);
            System.out.println("테스트 회원 생성 완료: ID = " + member.getId());
        } else {
            member = memberRepository.findById(1L).orElse(null);
        }
        
        // 테스트용 가게 데이터
        Store store1 = null;
        Store store2 = null;
        if (storeRepository.count() == 0) {
            store1 = Store.builder()
                    .name("반이학생마라탕마라반")
                    .address("서울시 강남구 테헤란로 123")
                    .description("정말 맛있는 마라탕 식당입니다!")
                    .rating(4.5f)
                    .build();
            
            store2 = Store.builder()
                    .name("행복한 카페")
                    .address("서울시 서초구 반포대로 456")
                    .description("분위기 좋은 카페입니다!")
                    .rating(4.8f)
                    .build();
            
            storeRepository.save(store1);
            storeRepository.save(store2);
            System.out.println("테스트 가게 생성 완료");
        } else {
            store1 = storeRepository.findById(1L).orElse(null);
            store2 = storeRepository.findById(2L).orElse(null);
        }
        
        // 테스트용 미션 데이터
        if (missionRepository.count() == 0 && store1 != null && store2 != null) {
            for (int i = 1; i <= 15; i++) {
                Mission mission = Mission.builder()
                        .title("미션 " + i + ": 3회 방문하기")
                        .description("맛있는 식당에 3회 방문하면 포인트 적립!")
                        .reward(1000 * i)
                        .deadline(LocalDate.now().plusMonths(1))
                        .build();
                mission.setStore(store1);
                missionRepository.save(mission);
            }
            
            for (int i = 1; i <= 10; i++) {
                Mission mission = Mission.builder()
                        .title("카페 미션 " + i + ": 커피 구매하기")
                        .description("커피 " + i + "잔 구매하면 포인트 적립!")
                        .reward(500 * i)
                        .deadline(LocalDate.now().plusMonths(2))
                        .build();
                mission.setStore(store2);
                missionRepository.save(mission);
            }
            System.out.println("테스트 미션 생성 완료");
        }
        
        // 테스트용 리뷰 데이터
        if (reviewRepository.count() == 0 && member != null && store1 != null) {
            for (int i = 1; i <= 25; i++) {
                Review review = Review.builder()
                        .rating(4.5f)
                        .content("음 너무 맛있어요 포인트도 얻고 맛있는 맛집도 알게 된 것 같아 너무너무 행복한 식사였습니다. 다음에 또 올게요!! (리뷰 " + i + ")")
                        .build();
                review.setMember(member);
                review.setStore(store1);
                reviewRepository.save(review);
            }
            System.out.println("테스트 리뷰 생성 완료");
        }
        
        // 테스트용 회원 미션 데이터 (진행중인 미션)
        if (memberMissionRepository.count() == 0 && member != null) {
            var missions = missionRepository.findAll();
            int count = 0;
            for (Mission mission : missions) {
                if (count >= 15) break;
                MemberMission memberMission = MemberMission.builder()
                        .status(MissionStatus.CHALLENGING)
                        .build();
                memberMission.setMember(member);
                memberMission.setMission(mission);
                memberMissionRepository.save(memberMission);
                count++;
            }
            System.out.println("테스트 회원 미션 생성 완료");
        }
    }
}
