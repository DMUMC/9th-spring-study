package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.review.dto.ReviewResponse;
import com.example.umc9th.domain.review.entity.QReview;
import com.example.umc9th.domain.store.entity.QStore;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReviewRepository {

    private final JPAQueryFactory queryFactory;

    public List<ReviewResponse> findMyReviews(
            Long memberId,
            String storeName,
            Integer minStar,
            Integer maxStar
    ) {
        QReview review = QReview.review;
        QStore store = QStore.store;

        BooleanBuilder where = new BooleanBuilder();
        where.and(review.member.id.eq(memberId));

        if (storeName != null && !storeName.isBlank()) {
            where.and(store.name.eq(storeName));
        }

        if (minStar != null && maxStar != null) {
            where.and(review.rating.between(minStar, maxStar));
        }

        return queryFactory
                .select(Projections.constructor(
                        ReviewResponse.class,
                        review.id,
                        store.name,
                        review.rating,
                        review.content,
                        review.createdAt        // LocalDateTime createdAt (BaseEntity 상속)
                ))
                .from(review)
                .join(review.store, store)
                .where(where)
                .orderBy(review.createdAt.desc())
                .fetch();
    }
}
