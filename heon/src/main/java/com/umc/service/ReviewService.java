package com.umc.service;

import com.umc.converter.ReviewConverter;
import com.umc.domain.Member;
import com.umc.domain.Review;
import com.umc.domain.Store;
import com.umc.dto.request.ReviewRequestDto;
import com.umc.dto.response.ReviewResponseDTO;
import com.umc.dto.response.SimpleReviewResponseDto;
import com.umc.repository.MemberRepository;
import com.umc.repository.ReviewRepository;
import com.umc.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
    public SimpleReviewResponseDto createReview(ReviewRequestDto requestDto) {
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
        
        return SimpleReviewResponseDto.from(savedReview);
    }
    
    public List<SimpleReviewResponseDto> getStoreReviews(Long storeId) {
        return reviewRepository.findByStoreId(storeId).stream()
                .map(SimpleReviewResponseDto::from)
                .collect(Collectors.toList());
    }
    
    public List<SimpleReviewResponseDto> getMyReviews() {
        return reviewRepository.findByMemberId(HARDCODED_MEMBER_ID).stream()
                .map(SimpleReviewResponseDto::from)
                .collect(Collectors.toList());
    }
    
    /**
     * 내가 작성한 리뷰 목록 (페이징)
     */
    public ReviewResponseDTO.ReviewPreviewListDTO getMyReviewsPaged(Long memberId, Integer page) {
        memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("회원을 찾을 수 없습니다. ID: " + memberId));
        
        PageRequest pageRequest = PageRequest.of(page - 1, 10, Sort.by(Sort.Direction.DESC, "createdAt"));
        Page<Review> reviewPage = reviewRepository.findByMemberId(memberId, pageRequest);
        
        return ReviewConverter.toReviewPreviewListDTO(reviewPage);
    }
}
