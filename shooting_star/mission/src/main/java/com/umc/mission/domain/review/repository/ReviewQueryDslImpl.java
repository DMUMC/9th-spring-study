package com.umc.mission.domain.review.repository;

import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.umc.mission.domain.review.entity.QReview;
import com.umc.mission.domain.review.entity.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReviewQueryDslImpl implements ReviewQueryDsl {
    private final JPAQueryFactory queryFactory;
    @Override
    public List<Review> searchMyReview(Predicate predicate){
        QReview review = QReview.review;

        return queryFactory
                .selectFrom(review)
                .where(predicate)
                .orderBy(review.id.desc())
                .fetch();
    }
}
