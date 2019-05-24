package vn.elca.training.dom;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QGroup is a Querydsl query type for Group
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QGroup extends EntityPathBase<Group> {

    private static final long serialVersionUID = -1461546318L;

    public static final QGroup group = new QGroup("group1");

    public final NumberPath<Long> groupLeadId = createNumber("groupLeadId", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<Project, QProject> projects = this.<Project, QProject>createList("projects", Project.class, QProject.class, PathInits.DIRECT2);

    public final NumberPath<Integer> version = createNumber("version", Integer.class);

    public QGroup(String variable) {
        super(Group.class, forVariable(variable));
    }

    public QGroup(Path<? extends Group> path) {
        super(path.getType(), path.getMetadata());
    }

    public QGroup(PathMetadata<?> metadata) {
        super(Group.class, metadata);
    }

}

