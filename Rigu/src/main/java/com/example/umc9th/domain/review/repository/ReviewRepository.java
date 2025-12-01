package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.review.dto.ReviewResponse;
import com.example.umc9th.domain.review.entity.QReview;
import com.example.umc9th.domain.store.entity.QStore;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReviewRepository {

    private final JPAQueryFactory queryFactory;

    public Page<ReviewResponse> findMyReviews(
            Long memberId,
            String storeName,
            Integer minStar,
            Integer maxStar,
            Pageable pageable
    ) {
        QReview review = QReview.review;
        QStore store = QStore.store;

        BooleanBuilder where = new BooleanBuilder();
        where.and(review.member.id.eq(memberId));
        where.and(review.isDeleted.isFalse());

        if (storeName != null && !storeName.isBlank()) {
            where.and(store.name.eq(storeName));
        }

        if (minStar != null && maxStar != null) {
            where.and(review.rating.between(minStar, maxStar));
        }

        List<ReviewResponse> content = queryFactory
                .select(Projections.constructor(
                        ReviewResponse.class,
                        review.id,
                        store.name,
                        review.rating,
                        review.content,
                        review.createdAt
                ))
                .from(review)
                .join(review.store, store)
                .where(where)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(review.createdAt.desc())
                .fetch();

        Long total = queryFactory
                .select(review.count())
                .from(review)
                .join(review.store, store)
                .where(where)
                .fetchOne();

        long totalCount = (total != null) ? total : 0L;

        return new PageImpl<>(content, pageable, totalCount);
    }
}
