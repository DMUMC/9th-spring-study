package com.example.dobee.domain.review.service.command;

import com.example.dobee.domain.member.entity.Member;
import com.example.dobee.domain.member.repository.MemberRepository;
import com.example.dobee.domain.review.converter.ReviewConverter;
import com.example.dobee.domain.review.dto.ReviewDto;
import com.example.dobee.domain.review.entity.Review;
import com.example.dobee.domain.review.repository.ReviewRepository;
import com.example.dobee.domain.store.entity.Store;
import com.example.dobee.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewCommandServiceImpl implements ReviewCommandService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    @Override
    public Review addReview(ReviewDto.AddReviewReqDto request) {
        Member member = memberRepository.findById(request.memberId()).get();
        Store store = storeRepository.findById(request.storeId()).get();
        Review newReview = ReviewConverter.toReview(request, member, store);
        return reviewRepository.save(newReview);
    }
}
