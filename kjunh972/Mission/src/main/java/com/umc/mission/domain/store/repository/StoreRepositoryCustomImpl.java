package com.umc.mission.domain.store.repository;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.umc.mission.domain.store.entity.Store;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.umc.mission.domain.store.entity.QStore.store;

@RequiredArgsConstructor
public class StoreRepositoryCustomImpl implements StoreRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Store> searchStores(Long regionId, List<Long> regionIds, String searchKeyword,
                                   String sortBy, Pageable pageable) {
        if (searchKeyword != null && searchKeyword.contains(" ")) {
            String[] keywords = searchKeyword.trim().split("\\s+");
            return searchStoresWithMultipleKeywords(keywords, regionId, regionIds, sortBy, pageable);
        } else {
            return searchStoresWithSingleKeyword(searchKeyword, regionId, regionIds, sortBy, pageable);
        }
    }

    @Override
    public Page<Store> searchStoresWithMultipleKeywords(String[] keywords, Long regionId,
                                                       List<Long> regionIds, String sortBy,
                                                       Pageable pageable) {
        BooleanExpression predicate = buildRegionPredicate(regionId, regionIds);

        BooleanExpression keywordPredicate = null;
        for (String keyword : keywords) {
            BooleanExpression keywordCondition = store.name.containsIgnoreCase(keyword);
            keywordPredicate = (keywordPredicate == null)
                    ? keywordCondition
                    : keywordPredicate.or(keywordCondition);
        }

        if (keywordPredicate != null && predicate != null) {
            predicate = predicate.and(keywordPredicate);
        } else if (keywordPredicate != null) {
            predicate = keywordPredicate;
        }

        return executeQuery(predicate, sortBy, pageable);
    }

    @Override
    public Page<Store> searchStoresWithSingleKeyword(String keyword, Long regionId,
                                                    List<Long> regionIds, String sortBy,
                                                    Pageable pageable) {
        BooleanExpression predicate = buildRegionPredicate(regionId, regionIds);

        if (keyword != null && !keyword.isEmpty()) {
            BooleanExpression keywordCondition = store.name.containsIgnoreCase(keyword);
            if (predicate != null) {
                predicate = predicate.and(keywordCondition);
            } else {
                predicate = keywordCondition;
            }
        }

        return executeQuery(predicate, sortBy, pageable);
    }

    /**
     * 지역 필터 조건 생성
     */
    private BooleanExpression buildRegionPredicate(Long regionId, List<Long> regionIds) {
        if (regionIds != null && !regionIds.isEmpty()) {
            return store.region.id.in(regionIds);
        }

        if (regionId != null) {
            return store.region.id.eq(regionId);
        }

        return null;
    }

    /**
     * 정렬 조건 생성
     * name 정렬: 한글 → 영어 대문자 → 영어 소문자 → 특수문자 순서, 동일 이름은 최신순
     */
    private OrderSpecifier<?>[] getOrderSpecifiers(String sortBy) {
        if ("name".equals(sortBy)) {
            // 한글 → 영어 대문자 → 영어 소문자 → 특수문자 순서로 정렬
            return new OrderSpecifier[]{
                    new OrderSpecifier<>(Order.ASC, buildCharacterTypePriority()),
                    new OrderSpecifier<>(Order.ASC, store.name),
                    new OrderSpecifier<>(Order.DESC, store.createdAt)
            };
        } else {
            // latest: 최신순
            return new OrderSpecifier[]{
                    new OrderSpecifier<>(Order.DESC, store.createdAt)
            };
        }
    }

    /**
     * 문자 종류에 따른 정렬 우선순위를 반환
     * 한글(1) → 영어 대문자(2) → 영어 소문자(3) → 특수문자(4)
     */
    private com.querydsl.core.types.dsl.NumberExpression<Integer> buildCharacterTypePriority() {
        return new CaseBuilder()
                // 한글: U+AC00(가) ~ U+D7A3(힣)
                .when(store.name.substring(0, 1).matches("[\\uAC00-\\uD7A3]"))
                .then(1)
                // 영어 대문자: A-Z
                .when(store.name.substring(0, 1).matches("[A-Z]"))
                .then(2)
                // 영어 소문자: a-z
                .when(store.name.substring(0, 1).matches("[a-z]"))
                .then(3)
                // 기타 (특수문자, 숫자 등)
                .otherwise(4)
                .castToNum(Integer.class);
    }

    /**
     * 쿼리 실행 및 페이징 처리
     */
    private Page<Store> executeQuery(BooleanExpression predicate, String sortBy, Pageable pageable) {
        Long countResult = queryFactory
                .select(store.count())
                .from(store)
                .where(predicate)
                .fetchOne();
        long total = countResult == null ? 0 : countResult;

        OrderSpecifier<?>[] orderSpecifiers = getOrderSpecifiers(sortBy);

        List<Store> content = queryFactory
                .selectFrom(store)
                .where(predicate)
                .orderBy(orderSpecifiers)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return new PageImpl<>(content, pageable, total);
    }
}