package com.umc.mission.domain.mission.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMemberMission is a Querydsl query type for MemberMission
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberMission extends EntityPathBase<MemberMission> {

    private static final long serialVersionUID = -1003557823L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMemberMission memberMission = new QMemberMission("memberMission");

    public final com.umc.mission.global.entity.QBaseEntity _super = new com.umc.mission.global.entity.QBaseEntity(this);

    public final DateTimePath<java.time.LocalDateTime> completedAt = createDateTime("completedAt", java.time.LocalDateTime.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final DateTimePath<java.time.LocalDateTime> deadlineAt = createDateTime("deadlineAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.umc.mission.domain.member.entity.QMember member;

    public final QMission mission;

    public final ListPath<com.umc.mission.domain.point.entity.PointHistory, com.umc.mission.domain.point.entity.QPointHistory> pointHistories = this.<com.umc.mission.domain.point.entity.PointHistory, com.umc.mission.domain.point.entity.QPointHistory>createList("pointHistories", com.umc.mission.domain.point.entity.PointHistory.class, com.umc.mission.domain.point.entity.QPointHistory.class, PathInits.DIRECT2);

    public final ListPath<com.umc.mission.domain.review.entity.Review, com.umc.mission.domain.review.entity.QReview> reviews = this.<com.umc.mission.domain.review.entity.Review, com.umc.mission.domain.review.entity.QReview>createList("reviews", com.umc.mission.domain.review.entity.Review.class, com.umc.mission.domain.review.entity.QReview.class, PathInits.DIRECT2);

    public final DateTimePath<java.time.LocalDateTime> startedAt = createDateTime("startedAt", java.time.LocalDateTime.class);

    public final EnumPath<com.umc.mission.domain.mission.enums.MemberMissionStatus> status = createEnum("status", com.umc.mission.domain.mission.enums.MemberMissionStatus.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QMemberMission(String variable) {
        this(MemberMission.class, forVariable(variable), INITS);
    }

    public QMemberMission(Path<? extends MemberMission> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMemberMission(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMemberMission(PathMetadata metadata, PathInits inits) {
        this(MemberMission.class, metadata, inits);
    }

    public QMemberMission(Class<? extends MemberMission> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new com.umc.mission.domain.member.entity.QMember(forProperty("member")) : null;
        this.mission = inits.isInitialized("mission") ? new QMission(forProperty("mission"), inits.get("mission")) : null;
    }

}

