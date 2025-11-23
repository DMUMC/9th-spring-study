package com.umc.service;

import com.umc.domain.Member;
import com.umc.domain.Review;
import com.umc.domain.Store;
import com.umc.dto.request.ReviewRequestDto;
import com.umc.dto.response.ReviewResponseDto;
import com.umc.repository.MemberRepository;
import com.umc.repository.ReviewRepository;
import com.umc.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {
    
    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;
    
    // 하드코딩된 회원 ID (실제로는 로그인 기능에서 가져와야 함)
    private static final Long HARDCODED_MEMBER_ID = 1L;
    
    @Transactional
    public ReviewResponseDto createReview(ReviewRequestDto requestDto) {
        // 1. 하드코딩된 회원 조회
        Member member = memberRepository.findById(HARDCODED_MEMBER_ID)
                .orElseThrow(() -> new IllegalArgumentException("회원을 찾을 수 없습니다. ID: " + HARDCODED_MEMBER_ID));
        
        // 2. 가게 조회
        Store store = storeRepository.findById(requestDto.getStoreId())
                .orElseThrow(() -> new IllegalArgumentException("가게를 찾을 수 없습니다. ID: " + requestDto.getStoreId()));
        
        // 3. 리뷰 생성
        Review review = Review.builder()
                .rating(requestDto.getRating())
                .content(requestDto.getContent())
                .build();
        
        review.setMember(member);
        review.setStore(store);
        
        // 4. 저장
        Review savedReview = reviewRepository.save(review);
        
        return ReviewResponseDto.from(savedReview);
    }
    
    public List<ReviewResponseDto> getStoreReviews(Long storeId) {
        return reviewRepository.findByStoreId(storeId).stream()
                .map(ReviewResponseDto::from)
                .collect(Collectors.toList());
    }
    
    public List<ReviewResponseDto> getMyReviews() {
        return reviewRepository.findByMemberId(HARDCODED_MEMBER_ID).stream()
                .map(ReviewResponseDto::from)
                .collect(Collectors.toList());
    }
}
