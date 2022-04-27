package test.java.integration.classic.compile.success;

import io.github.afezeria.freedao.annotation.Dao;
import test.Person;

/**
 *
 */
@Dao(crudEntity = Person.class)
public interface TooManyResultCheckDao {
    Person selectOneByName(String name);
}
