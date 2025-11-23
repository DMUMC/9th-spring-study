package com.example.umc9th.domain.mapping.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.example.umc9th.domain.mission.QMission;
import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.annotations.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserMission is a Querydsl query type for UserMission
 */
@SuppressWarnings("this-escape")
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserMission extends EntityPathBase<UserMission> {

    private static final long serialVersionUID = -1877232699L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserMission userMission = new QUserMission("userMission");

    public final com.example.umc9th.global.entity.QBaseEntity _super = new com.example.umc9th.global.entity.QBaseEntity(this);

    public final DateTimePath<java.time.LocalDateTime> completedAt = createDateTime("completedAt", java.time.LocalDateTime.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isComplete = createBoolean("isComplete");

    public final DateTimePath<java.time.LocalDateTime> joinedAt = createDateTime("joinedAt", java.time.LocalDateTime.class);

    public final com.example.umc9th.domain.member.entity.QMember member;

    public final QMission mission;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QUserMission(String variable) {
        this(UserMission.class, forVariable(variable), INITS);
    }

    public QUserMission(Path<? extends UserMission> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserMission(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserMission(PathMetadata metadata, PathInits inits) {
        this(UserMission.class, metadata, inits);
    }

    public QUserMission(Class<? extends UserMission> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new com.example.umc9th.domain.member.entity.QMember(forProperty("member")) : null;
        this.mission = inits.isInitialized("mission") ? new QMission(forProperty("mission"), inits.get("mission")) : null;
    }

}

