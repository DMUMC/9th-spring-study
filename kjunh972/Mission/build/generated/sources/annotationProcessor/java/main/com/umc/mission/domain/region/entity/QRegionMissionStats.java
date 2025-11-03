package com.umc.mission.domain.region.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRegionMissionStats is a Querydsl query type for RegionMissionStats
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRegionMissionStats extends EntityPathBase<RegionMissionStats> {

    private static final long serialVersionUID = -112765040L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRegionMissionStats regionMissionStats = new QRegionMissionStats("regionMissionStats");

    public final com.umc.mission.global.entity.QBaseEntity _super = new com.umc.mission.global.entity.QBaseEntity(this);

    public final NumberPath<Integer> completedMissions = createNumber("completedMissions", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isAllCompleted = createBoolean("isAllCompleted");

    public final com.umc.mission.domain.member.entity.QMember member;

    public final QRegion region;

    public final BooleanPath rewardGiven = createBoolean("rewardGiven");

    public final NumberPath<Integer> totalMissions = createNumber("totalMissions", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QRegionMissionStats(String variable) {
        this(RegionMissionStats.class, forVariable(variable), INITS);
    }

    public QRegionMissionStats(Path<? extends RegionMissionStats> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRegionMissionStats(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRegionMissionStats(PathMetadata metadata, PathInits inits) {
        this(RegionMissionStats.class, metadata, inits);
    }

    public QRegionMissionStats(Class<? extends RegionMissionStats> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new com.umc.mission.domain.member.entity.QMember(forProperty("member")) : null;
        this.region = inits.isInitialized("region") ? new QRegion(forProperty("region")) : null;
    }

}

