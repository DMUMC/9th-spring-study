package com.umc.mission.domain.store.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStore is a Querydsl query type for Store
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStore extends EntityPathBase<Store> {

    private static final long serialVersionUID = -1728786597L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStore store = new QStore("store");

    public final com.umc.mission.global.entity.QBaseEntity _super = new com.umc.mission.global.entity.QBaseEntity(this);

    public final StringPath address = createString("address");

    public final QStoreCategory category;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imageUrl = createString("imageUrl");

    public final NumberPath<java.math.BigDecimal> latitude = createNumber("latitude", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> longitude = createNumber("longitude", java.math.BigDecimal.class);

    public final ListPath<com.umc.mission.domain.mission.entity.Mission, com.umc.mission.domain.mission.entity.QMission> missions = this.<com.umc.mission.domain.mission.entity.Mission, com.umc.mission.domain.mission.entity.QMission>createList("missions", com.umc.mission.domain.mission.entity.Mission.class, com.umc.mission.domain.mission.entity.QMission.class, PathInits.DIRECT2);

    public final StringPath name = createString("name");

    public final StringPath phoneNum = createString("phoneNum");

    public final NumberPath<java.math.BigDecimal> rating = createNumber("rating", java.math.BigDecimal.class);

    public final com.umc.mission.domain.region.entity.QRegion region;

    public final NumberPath<Integer> reviewCount = createNumber("reviewCount", Integer.class);

    public final ListPath<com.umc.mission.domain.review.entity.Review, com.umc.mission.domain.review.entity.QReview> reviews = this.<com.umc.mission.domain.review.entity.Review, com.umc.mission.domain.review.entity.QReview>createList("reviews", com.umc.mission.domain.review.entity.Review.class, com.umc.mission.domain.review.entity.QReview.class, PathInits.DIRECT2);

    public final ListPath<StoreImage, QStoreImage> storeImages = this.<StoreImage, QStoreImage>createList("storeImages", StoreImage.class, QStoreImage.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QStore(String variable) {
        this(Store.class, forVariable(variable), INITS);
    }

    public QStore(Path<? extends Store> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStore(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStore(PathMetadata metadata, PathInits inits) {
        this(Store.class, metadata, inits);
    }

    public QStore(Class<? extends Store> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.category = inits.isInitialized("category") ? new QStoreCategory(forProperty("category")) : null;
        this.region = inits.isInitialized("region") ? new com.umc.mission.domain.region.entity.QRegion(forProperty("region")) : null;
    }

}

