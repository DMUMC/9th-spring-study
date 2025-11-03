package com.umc.mission.domain.member.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = 1866324297L;

    public static final QMember member = new QMember("member1");

    public final com.umc.mission.global.entity.QBaseEntity _super = new com.umc.mission.global.entity.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath email = createString("email");

    public final StringPath gender = createString("gender");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.time.LocalDateTime> inactiveDate = createDateTime("inactiveDate", java.time.LocalDateTime.class);

    public final SetPath<com.umc.mission.domain.mission.entity.MemberMission, com.umc.mission.domain.mission.entity.QMemberMission> memberMissions = this.<com.umc.mission.domain.mission.entity.MemberMission, com.umc.mission.domain.mission.entity.QMemberMission>createSet("memberMissions", com.umc.mission.domain.mission.entity.MemberMission.class, com.umc.mission.domain.mission.entity.QMemberMission.class, PathInits.DIRECT2);

    public final StringPath name = createString("name");

    public final StringPath nickname = createString("nickname");

    public final StringPath phoneNum = createString("phoneNum");

    public final SetPath<com.umc.mission.domain.point.entity.PointHistory, com.umc.mission.domain.point.entity.QPointHistory> pointHistories = this.<com.umc.mission.domain.point.entity.PointHistory, com.umc.mission.domain.point.entity.QPointHistory>createSet("pointHistories", com.umc.mission.domain.point.entity.PointHistory.class, com.umc.mission.domain.point.entity.QPointHistory.class, PathInits.DIRECT2);

    public final StringPath profileImage = createString("profileImage");

    public final SetPath<com.umc.mission.domain.region.entity.RegionMissionStats, com.umc.mission.domain.region.entity.QRegionMissionStats> regionMissionStats = this.<com.umc.mission.domain.region.entity.RegionMissionStats, com.umc.mission.domain.region.entity.QRegionMissionStats>createSet("regionMissionStats", com.umc.mission.domain.region.entity.RegionMissionStats.class, com.umc.mission.domain.region.entity.QRegionMissionStats.class, PathInits.DIRECT2);

    public final SetPath<com.umc.mission.domain.review.entity.Review, com.umc.mission.domain.review.entity.QReview> reviews = this.<com.umc.mission.domain.review.entity.Review, com.umc.mission.domain.review.entity.QReview>createSet("reviews", com.umc.mission.domain.review.entity.Review.class, com.umc.mission.domain.review.entity.QReview.class, PathInits.DIRECT2);

    public final StringPath socialType = createString("socialType");

    public final EnumPath<com.umc.mission.domain.member.enums.MemberStatus> status = createEnum("status", com.umc.mission.domain.member.enums.MemberStatus.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QMember(String variable) {
        super(Member.class, forVariable(variable));
    }

    public QMember(Path<? extends Member> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMember(PathMetadata metadata) {
        super(Member.class, metadata);
    }

}

