package com.umc.mission.domain.review.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.umc.mission.domain.review.entity.Review;
import com.umc.mission.domain.review.enums.ReviewStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;

import static com.umc.mission.domain.review.entity.QReview.review;

@RequiredArgsConstructor
public class ReviewRepositoryCustomImpl implements ReviewRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Review> findMyReviewsByFilters(Long memberId, Long storeId,
                                               BigDecimal minRating, BigDecimal maxRating,
                                               Pageable pageable) {
        BooleanExpression predicate = review.member.id.eq(memberId)
                .and(review.status.eq(ReviewStatus.ACTIVE));

        if (storeId != null) {
            predicate = predicate.and(review.store.id.eq(storeId));
        }

        if (minRating != null) {
            predicate = predicate.and(review.rating.goe(minRating));
        }

        if (maxRating != null) {
            predicate = predicate.and(review.rating.loe(maxRating));
        }

        Long countResult = queryFactory
                .select(review.count())
                .from(review)
                .where(predicate)
                .fetchOne();
        long total = countResult == null ? 0 : countResult;

        List<Review> content = queryFactory
                .selectFrom(review)
                .where(predicate)
                .orderBy(review.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return new PageImpl<>(content, pageable, total);
    }
}