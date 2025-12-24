package com.example.umc9th.domain.term.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.dsl.StringTemplate;

import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.annotations.Generated;
import com.querydsl.core.types.Path;


/**
 * QTerm is a Querydsl query type for Term
 */
@SuppressWarnings("this-escape")
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTerm extends EntityPathBase<Term> {

    private static final long serialVersionUID = -1532508924L;

    public static final QTerm term = new QTerm("term");

    public final com.example.umc9th.global.entity.QBaseEntity _super = new com.example.umc9th.global.entity.QBaseEntity(this);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isRequired = createBoolean("isRequired");

    public final StringPath name = createString("name");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QTerm(String variable) {
        super(Term.class, forVariable(variable));
    }

    public QTerm(Path<? extends Term> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTerm(PathMetadata metadata) {
        super(Term.class, metadata);
    }

}

