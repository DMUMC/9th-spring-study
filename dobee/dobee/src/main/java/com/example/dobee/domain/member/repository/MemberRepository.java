package com.example.dobee.domain.member.repository;

import com.example.dobee.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findByNameAndDeletedAtIsNull(String name);

    @Query("SELECT m FROM Member m WHERE m.name = :name AND m.deletedAt IS null")
    List<Member> findActiveMember(@Param("name") String name);

    Optional<Member> findByEmail(String email);
}
