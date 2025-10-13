package com.example.dongdong.domain.qna.repository;

import com.example.dongdong.domain.qna.entity.Qna;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QnaRepository extends JpaRepository<Qna, Long> {

    // 특정 키워드를 제목에 포함하는 QnA 조회
    @Query("SELECT q FROM Qna q WHERE q.title LIKE %:keyword%")
    List<Qna> findByTitleContaining(@Param("keyword") String keyword);
}