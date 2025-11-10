package com.example.dobee.domain.review.repository;


import com.example.dobee.domain.review.dto.ReviewDto;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.expression.spel.ast.Projection;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.List;

import static com.example.dobee.domain.member.entity.QMember.member;
import static com.example.dobee.domain.review.entity.QReview.review;
import static com.example.dobee.domain.store.entity.QStore.store;

@Service
@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<ReviewDto.Response> findByReviews(Long memberId, String storeName, Integer rating, Pageable pageable) {
        List<ReviewDto.Response> content = queryFactory
                .select(Projections.constructor(ReviewDto.Response.class,
                        review.id,
                        member.id,
                        store.id,
                        store.name,
                        review.rating,
                        review.content,
                        review.status.stringValue(),
                        review.createdAt
                ))
                .from(review)
                .join(review.member, member)
                .join(review.store, store)
                .where(
                        review.member.id.eq(memberId),
                        storeNameEq(storeName),
                        ratingEq(rating)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(review.createdAt.desc())
                .fetch();

        Long count = queryFactory
                .select(review.count())
                .from(review)
                .join(review.member, member)
                .join(review.store, store)
                .where(
                        review.member.id.eq(memberId),
                        storeNameEq(storeName),
                        ratingEq(rating)
                )
                .fetchOne();

        return new PageImpl<>(content, pageable, count);
    }

    private BooleanExpression storeNameEq(String storeName) {
        return StringUtils.hasText(storeName) ? store.name.contains(storeName) : null;
    }

    private BooleanExpression ratingEq(Integer rating) {
        if (rating == null) {
            return null;
        }
        if (rating == 5) {
            return review.rating.eq(new BigDecimal("5.0"));
        }
        return review.rating.goe(new BigDecimal(rating)).and(review.rating.lt(new BigDecimal(rating + 1)));
    }
}
