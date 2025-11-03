package com.umc.mission.domain.review.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReview is a Querydsl query type for Review
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReview extends EntityPathBase<Review> {

    private static final long serialVersionUID = -587035067L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReview review = new QReview("review");

    public final com.umc.mission.global.entity.QBaseEntity _super = new com.umc.mission.global.entity.QBaseEntity(this);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final DateTimePath<java.time.LocalDateTime> deletedAt = createDateTime("deletedAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.umc.mission.domain.member.entity.QMember member;

    public final com.umc.mission.domain.mission.entity.QMemberMission memberMission;

    public final NumberPath<java.math.BigDecimal> rating = createNumber("rating", java.math.BigDecimal.class);

    public final ListPath<ReviewImage, QReviewImage> reviewImages = this.<ReviewImage, QReviewImage>createList("reviewImages", ReviewImage.class, QReviewImage.class, PathInits.DIRECT2);

    public final EnumPath<com.umc.mission.domain.review.enums.ReviewStatus> status = createEnum("status", com.umc.mission.domain.review.enums.ReviewStatus.class);

    public final com.umc.mission.domain.store.entity.QStore store;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QReview(String variable) {
        this(Review.class, forVariable(variable), INITS);
    }

    public QReview(Path<? extends Review> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReview(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReview(PathMetadata metadata, PathInits inits) {
        this(Review.class, metadata, inits);
    }

    public QReview(Class<? extends Review> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new com.umc.mission.domain.member.entity.QMember(forProperty("member")) : null;
        this.memberMission = inits.isInitialized("memberMission") ? new com.umc.mission.domain.mission.entity.QMemberMission(forProperty("memberMission"), inits.get("memberMission")) : null;
        this.store = inits.isInitialized("store") ? new com.umc.mission.domain.store.entity.QStore(forProperty("store"), inits.get("store")) : null;
    }

}

