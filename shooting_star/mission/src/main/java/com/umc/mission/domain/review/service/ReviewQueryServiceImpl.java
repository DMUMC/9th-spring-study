package com.umc.mission.domain.review.service;

import com.umc.mission.domain.member.exception.code.MemberErrorCode;
import com.umc.mission.domain.member.exception.code.MemberException;
import com.umc.mission.domain.member.repository.MemberRepository;
import com.umc.mission.domain.review.entity.Review;
import com.umc.mission.domain.review.exception.ReviewException;
import com.umc.mission.domain.review.repository.ReviewRepository;
import com.umc.mission.domain.store.exception.StoreErrorCode;
import com.umc.mission.domain.store.exception.StoreHandler;
import com.umc.mission.domain.store.repository.StoreRepository;
import com.umc.mission.global.apiPayload.code.PageErrorCode;
import com.umc.mission.global.apiPayload.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewQueryServiceImpl implements ReviewQueryService {
    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;

    @Override
    // 조회 전용이므로 읽기 전용 옵션(성능 최적화)
    @Transactional(readOnly = true)
    public Page<Review> findReviewByStoreId(Long storeId, Integer page) {
        if(page == null || page < 1) {
            throw new GeneralException(PageErrorCode.BAD_REQUEST);
        }

        // 가게의 존재 여부 먼저 확인
        storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreHandler(StoreErrorCode.NOT_FOUND));

        return reviewRepository.findByStoreId(storeId, PageRequest.of(page - 1, 5));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Review> findReviewByMemberId(Long memberId, Integer page) {
        memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));

        return reviewRepository.findByMemberId(memberId, PageRequest.of(page, 5));
    }
}
