package com.umc.mission.domain.region.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRegion is a Querydsl query type for Region
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRegion extends EntityPathBase<Region> {

    private static final long serialVersionUID = -9737091L;

    public static final QRegion region = new QRegion("region");

    public final com.umc.mission.global.entity.QBaseEntity _super = new com.umc.mission.global.entity.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final ListPath<RegionMissionStats, QRegionMissionStats> regionMissionStats = this.<RegionMissionStats, QRegionMissionStats>createList("regionMissionStats", RegionMissionStats.class, QRegionMissionStats.class, PathInits.DIRECT2);

    public final ListPath<com.umc.mission.domain.store.entity.Store, com.umc.mission.domain.store.entity.QStore> stores = this.<com.umc.mission.domain.store.entity.Store, com.umc.mission.domain.store.entity.QStore>createList("stores", com.umc.mission.domain.store.entity.Store.class, com.umc.mission.domain.store.entity.QStore.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QRegion(String variable) {
        super(Region.class, forVariable(variable));
    }

    public QRegion(Path<? extends Region> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRegion(PathMetadata metadata) {
        super(Region.class, metadata);
    }

}

