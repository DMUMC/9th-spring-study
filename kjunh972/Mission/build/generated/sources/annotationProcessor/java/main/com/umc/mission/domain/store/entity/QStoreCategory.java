package com.umc.mission.domain.store.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStoreCategory is a Querydsl query type for StoreCategory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStoreCategory extends EntityPathBase<StoreCategory> {

    private static final long serialVersionUID = 1978033529L;

    public static final QStoreCategory storeCategory = new QStoreCategory("storeCategory");

    public final com.umc.mission.global.entity.QBaseEntity _super = new com.umc.mission.global.entity.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final ListPath<Store, QStore> stores = this.<Store, QStore>createList("stores", Store.class, QStore.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QStoreCategory(String variable) {
        super(StoreCategory.class, forVariable(variable));
    }

    public QStoreCategory(Path<? extends StoreCategory> path) {
        super(path.getType(), path.getMetadata());
    }

    public QStoreCategory(PathMetadata metadata) {
        super(StoreCategory.class, metadata);
    }

}

