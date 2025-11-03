package com.umc.mission.domain.store.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStoreImage is a Querydsl query type for StoreImage
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStoreImage extends EntityPathBase<StoreImage> {

    private static final long serialVersionUID = -1170912576L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStoreImage storeImage = new QStoreImage("storeImage");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imageUrl = createString("imageUrl");

    public final BooleanPath isMain = createBoolean("isMain");

    public final QStore store;

    public QStoreImage(String variable) {
        this(StoreImage.class, forVariable(variable), INITS);
    }

    public QStoreImage(Path<? extends StoreImage> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStoreImage(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStoreImage(PathMetadata metadata, PathInits inits) {
        this(StoreImage.class, metadata, inits);
    }

    public QStoreImage(Class<? extends StoreImage> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.store = inits.isInitialized("store") ? new QStore(forProperty("store"), inits.get("store")) : null;
    }

}

