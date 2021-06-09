package com.odod.gallery;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QGallery is a Querydsl query type for Gallery
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QGallery extends EntityPathBase<Gallery> {

    private static final long serialVersionUID = 1843408735L;

    public static final QGallery gallery = new QGallery("gallery");

    public final com.odod.common.QBaseTimeEntity _super = new com.odod.common.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final StringPath email = createString("email");

    public final StringPath filePath = createString("filePath");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Double> lat = createNumber("lat", Double.class);

    public final NumberPath<Double> lon = createNumber("lon", Double.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final StringPath subject = createString("subject");

    public QGallery(String variable) {
        super(Gallery.class, forVariable(variable));
    }

    public QGallery(Path<? extends Gallery> path) {
        super(path.getType(), path.getMetadata());
    }

    public QGallery(PathMetadata metadata) {
        super(Gallery.class, metadata);
    }

}

