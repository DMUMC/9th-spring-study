package com.example.umc9th.domain.mission.repository;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.mission.dto.AvailableMissionDTO;
import com.example.umc9th.domain.mission.dto.QAvailableMissionDTO;
import com.example.umc9th.domain.mission.entity.Mission;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

import static com.example.umc9th.domain.mission.entity.QMission.mission;
import static com.example.umc9th.domain.store.entity.QStore.store;
import static com.example.umc9th.domain.mission.entity.mapping.QMemberMission.memberMission;

// final 키워드가 붙은 필드를 파라미터로 받는 생성자 자동 생성
@RequiredArgsConstructor
public class MissionRepositoryImpl implements MissionRepositoryCustom{
    // 의존성 주입
    private final JPAQueryFactory queryFactory;

    @Override
    public List<AvailableMissionDTO> findAvailableMissionsByRegion(
            Member member,
            String region,
            Long cursorId,
            Pageable pageable
    ){
        // 커서 페이징을 위한 초기값 설정
        Long effecticeCursorId = (cursorId == null) ? Long.MAX_VALUE : cursorId;

        return queryFactory
                .select(new QAvailableMissionDTO(
                        mission.id,
                        mission.point,
                        mission.deadline,
                        store.name,
                        store.storeCategory
                ))
                .from(mission)
                .join(mission.store, store).fetchJoin()
                .leftJoin(memberMission).on(memberMission.mission.eq(mission).and(memberMission.member.eq(member)))
                .where(
                        store.storeAddress.like("%" + region + "%"), // 가게 주소에 region을 포함
                        mission.deadline.after(LocalDateTime.now()), // 마감일이 지나지 않음(마감일이 현재 시간보다 after인가?)
                        mission.id.lt(effecticeCursorId), // 커서 id보다 작은 데이터
                        memberMission.isNull() // 데이터가 없는 것만
                )
                .orderBy(mission.id.desc()) // 최신순 정렬
                .limit(pageable.getPageSize()) // 갯수 제한
                .fetch(); // 리스트로 결과 반환
    }
}