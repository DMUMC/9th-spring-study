package com.example.umc9th.domain.mapping.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.dsl.StringTemplate;

import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.annotations.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserTerm is a Querydsl query type for UserTerm
 */
@SuppressWarnings("this-escape")
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserTerm extends EntityPathBase<UserTerm> {

    private static final long serialVersionUID = 1705239763L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserTerm userTerm = new QUserTerm("userTerm");

    public final com.example.umc9th.global.entity.QBaseEntity _super = new com.example.umc9th.global.entity.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.example.umc9th.domain.member.entity.QMember member;

    public final com.example.umc9th.domain.term.entity.QTerm term;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QUserTerm(String variable) {
        this(UserTerm.class, forVariable(variable), INITS);
    }

    public QUserTerm(Path<? extends UserTerm> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserTerm(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserTerm(PathMetadata metadata, PathInits inits) {
        this(UserTerm.class, metadata, inits);
    }

    public QUserTerm(Class<? extends UserTerm> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new com.example.umc9th.domain.member.entity.QMember(forProperty("member")) : null;
        this.term = inits.isInitialized("term") ? new com.example.umc9th.domain.term.entity.QTerm(forProperty("term")) : null;
    }

}

