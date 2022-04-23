package test.java.integration.core.compile.success.method.resulthelper;

import com.github.afezeria.freedao.annotation.Dao;
import test.Person;

import java.util.Collection;

/**
 * @author afezeria
 */
@Dao(crudEntity = Person.class)
public interface QueryReturnCollectionDao {
    Collection<Person> list(Person person);
}