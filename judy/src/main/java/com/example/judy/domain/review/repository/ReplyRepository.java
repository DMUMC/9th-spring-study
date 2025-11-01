package com.example.judy.domain.review.repository;

import com.example.judy.domain.review.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<Reply,Long> {
}
