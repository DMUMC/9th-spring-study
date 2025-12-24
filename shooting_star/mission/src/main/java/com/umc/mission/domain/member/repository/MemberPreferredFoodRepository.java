package com.umc.mission.domain.member.repository;

import com.umc.mission.domain.member.entity.MemberPreferredFood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberPreferredFoodRepository extends JpaRepository<MemberPreferredFood, Long> {
}
