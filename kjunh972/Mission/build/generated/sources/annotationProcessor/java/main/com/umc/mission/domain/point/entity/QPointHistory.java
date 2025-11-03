package com.umc.mission.domain.point.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPointHistory is a Querydsl query type for PointHistory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPointHistory extends EntityPathBase<PointHistory> {

    private static final long serialVersionUID = 1078968409L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPointHistory pointHistory = new QPointHistory("pointHistory");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.umc.mission.domain.member.entity.QMember member;

    public final com.umc.mission.domain.mission.entity.QMemberMission memberMission;

    public final NumberPath<Integer> pointBalance = createNumber("pointBalance", Integer.class);

    public final NumberPath<Integer> pointChange = createNumber("pointChange", Integer.class);

    public final StringPath type = createString("type");

    public QPointHistory(String variable) {
        this(PointHistory.class, forVariable(variable), INITS);
    }

    public QPointHistory(Path<? extends PointHistory> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPointHistory(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPointHistory(PathMetadata metadata, PathInits inits) {
        this(PointHistory.class, metadata, inits);
    }

    public QPointHistory(Class<? extends PointHistory> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new com.umc.mission.domain.member.entity.QMember(forProperty("member")) : null;
        this.memberMission = inits.isInitialized("memberMission") ? new com.umc.mission.domain.mission.entity.QMemberMission(forProperty("memberMission"), inits.get("memberMission")) : null;
    }

}

