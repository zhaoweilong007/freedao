package test.java.integration.core.compile.failure.method.style.named;

import io.github.afezeria.freedao.annotation.Dao;
import test.Person;

/**
 * @author afezeria
 */
@Dao(crudEntity = Person.class)
public interface CountMethodContainSortBadDao {
    int countByOrderByIdAsc();
}
