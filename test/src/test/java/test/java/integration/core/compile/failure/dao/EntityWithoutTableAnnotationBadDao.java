package test.java.integration.core.compile.failure.dao;

import io.github.afezeria.freedao.annotation.Dao;
import test.WithoutTableAnnotation;

/**
 * @author afezeria
 */
@Dao(crudEntity = WithoutTableAnnotation.class)
public interface EntityWithoutTableAnnotationBadDao {
}
