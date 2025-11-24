package com.umc.mission.domain.review.service;

import com.umc.mission.domain.member.entity.Member;
import com.umc.mission.domain.member.exception.MemberNotFoundException;
import com.umc.mission.domain.member.repository.MemberRepository;
import com.umc.mission.domain.review.dto.ReviewDto;
import com.umc.mission.domain.review.entity.Review;
import com.umc.mission.domain.review.enums.ReviewStatus;
import com.umc.mission.domain.review.exception.InvalidRatingException;
import com.umc.mission.domain.review.exception.ReviewContentEmptyException;
import com.umc.mission.domain.review.repository.ReviewRepository;
import com.umc.mission.domain.store.entity.Store;
import com.umc.mission.domain.review.exception.StoreNotFoundException;
import com.umc.mission.domain.store.repository.StoreRepository;
import com.umc.mission.global.exception.InvalidPageException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    // 리뷰 작성
    @Transactional
    public void createReview(Long memberId, Long storeId, BigDecimal rating, String content) {
        if (memberId == null) throw new MemberNotFoundException("회원 ID는 필수입니다");
        if (storeId == null) throw new StoreNotFoundException("가게 ID는 필수입니다");
        if (rating == null) throw new InvalidRatingException("평점은 필수입니다");
        if (content == null || content.isBlank()) throw new ReviewContentEmptyException("리뷰 내용은 필수입니다");
        if (rating.compareTo(BigDecimal.ZERO) < 0 || rating.compareTo(BigDecimal.valueOf(5.0)) > 0) {
            throw new InvalidRatingException("평점은 0.0 ~ 5.0 사이여야 합니다");
        }

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException("Member not found with id: " + memberId));
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreNotFoundException("Store not found with id: " + storeId));

        Review review = Review.builder()
                .member(member)
                .store(store)
                .rating(rating)
                .content(content)
                .status(ReviewStatus.ACTIVE)
                .build();

        reviewRepository.save(review);
    }

    // 내가 작성한 리뷰 목록 조회 (페이징)
    @Transactional(readOnly = true)
    public ReviewDto.PageResponse getMyReviews(Long memberId, int page) {
        int size = 10;
        Page<Review> reviewPage = reviewRepository.findByMemberId(memberId, PageRequest.of(page - 1, size));
        return ReviewDto.PageResponse.from(reviewPage);
    }

    // 특정 회원의 리뷰 목록 조회
    @Transactional(readOnly = true)
    public Page<ReviewDto.Response> getReviewsMember(Long memberId, int page, int size) {
        return reviewRepository.findByMemberId(memberId, PageRequest.of(page, size))
                .map(ReviewDto.Response::from);
    }

    // 특정 가게의 리뷰 목록 조회
    @Transactional(readOnly = true)
    public Page<ReviewDto.Response> getReviewsStore(Long storeId, int page, int size) {
        return reviewRepository.findByStoreId(storeId, PageRequest.of(page, size))
                .map(ReviewDto.Response::from);
    }

    // 특정 회원의 활성화된 리뷰만 조회
    @Transactional(readOnly = true)
    public Page<ReviewDto.Response> getReviewsActive(Long memberId, int page, int size) {
        return reviewRepository.findByMemberIdAndStatus(memberId, ReviewStatus.ACTIVE, PageRequest.of(page, size))
                .map(ReviewDto.Response::from);
    }

    // 리뷰 개수 조회
    @Transactional(readOnly = true)
    public Long getReviewCount(Long memberId) {
        return reviewRepository.countByMemberId(memberId);
    }

    // QueryDSL을 사용한 회원의 리뷰 필터링 조회 (가게별, 별점별)
    @Transactional(readOnly = true)
    public Page<Review> getMyReviewsByFilters(Long memberId, Long storeId,
                                              BigDecimal minRating, BigDecimal maxRating,
                                              int page, int size) {
        return reviewRepository.findMyReviewsByFilters(memberId, storeId, minRating, maxRating, PageRequest.of(page, size));
    }
}