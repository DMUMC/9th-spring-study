package com.example.umc9th.domain.review.service;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.domain.review.dto.ReviewCreateRequest;
import com.example.umc9th.domain.review.dto.ReviewCreateResponse;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.review.repository.ReviewJpaRepository;

import com.example.umc9th.domain.store.entity.Store;

import com.example.umc9th.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewCommandService {

    private final ReviewJpaRepository reviewJpaRepository;
    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public ReviewCreateResponse addReview(Long storeId, ReviewCreateRequest request) {

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new RuntimeException("STORE_NOT_FOUND"));

        Member member = memberRepository.findById(request.memberId())
                .orElseThrow(() -> new RuntimeException("MEMBER_NOT_FOUND"));

        Review review = Review.builder()
                .store(store)
                .member(member)
                .rating(request.rating())
                .content(request.content())
                .build();

        Review saved = reviewJpaRepository.save(review);

        return new ReviewCreateResponse(saved.getId(), saved.getCreatedAt());
    }
}
