package com.example.umc9th.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 이 클래스가 스프링의 설정 파일임을 알림
// QueryDSL 쿼리를 만들고 실행하는 핵심 클래스가 JPAQueryFactory
@Configuration
public class QuerydslConfig {
    @PersistenceContext
    private EntityManager em;

    // 빈에 등록
    @Bean
    public JPAQueryFactory jpaQueryFactory() {
        return new JPAQueryFactory(em);
    }
}
