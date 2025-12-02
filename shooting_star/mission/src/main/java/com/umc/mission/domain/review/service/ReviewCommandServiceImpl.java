package com.umc.mission.domain.review.service;

import ch.qos.logback.core.status.ErrorStatus;
import com.umc.mission.domain.member.entity.Member;
import com.umc.mission.domain.member.exception.MemberNotFoundException;
import com.umc.mission.domain.member.exception.code.MemberErrorCode;
import com.umc.mission.domain.member.exception.code.MemberException;
import com.umc.mission.domain.member.repository.MemberRepository;
import com.umc.mission.domain.review.converter.ReviewConverter;
import com.umc.mission.domain.review.dto.StoreReviewReqDTO;
import com.umc.mission.domain.review.entity.Review;
import com.umc.mission.domain.review.repository.ReviewRepository;
import com.umc.mission.domain.store.entity.Store;
import com.umc.mission.domain.store.exception.StoreErrorCode;
import com.umc.mission.domain.store.exception.StoreHandler;
import com.umc.mission.domain.store.repository.StoreRepository;
import com.umc.mission.global.apiPayload.code.GeneralErrorCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewCommandServiceImpl implements ReviewCommandService {
    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    @Override
    @Transactional
    public Review createReview(
            Long memberId,
            Long storeId,
            StoreReviewReqDTO.CreateReviewDTO request
    ){
        // member 조회
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreHandler(StoreErrorCode.NOT_FOUND));

        Review review = ReviewConverter.toEntity(request, member, store);

        return reviewRepository.save(review);
    }
}
