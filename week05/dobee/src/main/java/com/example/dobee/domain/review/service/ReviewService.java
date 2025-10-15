package com.example.dobee.domain.review.service;

import com.example.dobee.domain.member.entity.Member;
import com.example.dobee.domain.member.repository.MemberRepository;
import com.example.dobee.domain.review.dto.request.ReviewRequestDto;
import com.example.dobee.domain.review.entity.Review;
import com.example.dobee.domain.review.repository.ReviewRepository;
import com.example.dobee.domain.store.entity.Store;
import com.example.dobee.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    @Transactional
    public Review createReview(ReviewRequestDto reviewRequestDto) {
        Member member = memberRepository.findById(reviewRequestDto.getStoreId())
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        Store store = storeRepository.findById(reviewRequestDto.getStoreId())
                .orElseThrow(() -> new IllegalArgumentException("해당 가게를 찾을 수 없습니다."));

        Review review = Review.builder()
                .member(member)
                .store(store)
                .rating(reviewRequestDto.getRating())
                .content(reviewRequestDto.getContent())
                .build();

        return reviewRepository.save(review);
    }
}
