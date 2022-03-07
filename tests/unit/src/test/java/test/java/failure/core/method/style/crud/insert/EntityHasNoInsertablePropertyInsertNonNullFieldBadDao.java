package test.java.failure.core.method.style.crud.insert;

import com.github.afezeria.freedao.annotation.Dao;
import test.PersonWithoutInsertableProperty;

/**
 * @author afezeria
 */
@Dao(crudEntity = PersonWithoutInsertableProperty.class)
public interface EntityHasNoInsertablePropertyInsertNonNullFieldBadDao {
    int insertNonNullFields(PersonWithoutInsertableProperty entity);
}
