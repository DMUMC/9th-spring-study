package com.example.dobee.domain.review.service.query;

import com.example.dobee.domain.review.converter.ReviewConverter;
import com.example.dobee.domain.review.dto.ReviewDto;
import com.example.dobee.domain.review.entity.Review;
import com.example.dobee.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewQueryServiceImpl implements ReviewQueryService {

    private final ReviewRepository reviewRepository;

    @Override
    public ReviewDto.ReviewPreViewListResDto getMyReviews(Long memberId, Integer page, String storeName, Integer rating) {
        Page<Review> reviewPage = reviewRepository.findMyReviews(memberId, storeName, rating, PageRequest.of(page - 1, 10));
        return ReviewConverter.toReviewPreViewListResDto(reviewPage);
    }
}
