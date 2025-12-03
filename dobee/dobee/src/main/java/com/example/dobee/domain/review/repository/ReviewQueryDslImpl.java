package com.example.dobee.domain.review.repository;

import com.example.dobee.domain.review.entity.QReview;
import com.example.dobee.domain.review.entity.Review;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReviewQueryDslImpl implements ReviewQueryDsl {

    private final EntityManager em;

    @Override
    public Page<Review> findMyReviews(Long userId, String storeName, Integer rating, Pageable pageable) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QReview review = QReview.review;

        BooleanBuilder builder = new BooleanBuilder();
        // TODO: 추후 인증 기능 추가 시 실제 사용자 ID를 가져오도록 수정
        builder.and(review.member.id.eq(userId));

        if (StringUtils.hasText(storeName)) {
            builder.and(review.store.name.eq(storeName));
        }

        if (rating != null) {
            builder.and(review.star.goe(rating));
            builder.and(review.star.lt(rating + 1));
        }

        List<Review> content = queryFactory
                .selectFrom(review)
                .where(builder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(review.count())
                .from(review)
                .where(builder);

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
    }
}