package com.example.umc9th.domain.mapping.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.dsl.StringTemplate;

import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.annotations.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserFood is a Querydsl query type for UserFood
 */
@SuppressWarnings("this-escape")
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserFood extends EntityPathBase<UserFood> {

    private static final long serialVersionUID = 1704832197L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserFood userFood = new QUserFood("userFood");

    public final com.example.umc9th.global.entity.QBaseEntity _super = new com.example.umc9th.global.entity.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final com.example.umc9th.domain.food.entity.QFood food;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.example.umc9th.domain.member.entity.QMember member;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QUserFood(String variable) {
        this(UserFood.class, forVariable(variable), INITS);
    }

    public QUserFood(Path<? extends UserFood> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserFood(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserFood(PathMetadata metadata, PathInits inits) {
        this(UserFood.class, metadata, inits);
    }

    public QUserFood(Class<? extends UserFood> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.food = inits.isInitialized("food") ? new com.example.umc9th.domain.food.entity.QFood(forProperty("food")) : null;
        this.member = inits.isInitialized("member") ? new com.example.umc9th.domain.member.entity.QMember(forProperty("member")) : null;
    }

}

