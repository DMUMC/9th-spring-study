package com.example.umc9th.domain.review.repository;

import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.store.entity.QStore;
import com.example.umc9th.domain.review.entity.QReview;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReviewRepository {

    private final JPAQueryFactory queryFactory;

    // ✅ 간단한 QueryDSL 쿼리
    public List<Review> findMyReviews(Long memberId, String storeName, Double minStar, Double maxStar) {
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
                .selectFrom(review)
                .join(review.store, store).fetchJoin()
                .where(where)
                .orderBy(review.createdAt.desc())
                .fetch();
    }
}
