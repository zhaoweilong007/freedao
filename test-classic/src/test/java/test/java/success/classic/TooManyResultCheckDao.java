package test.java.success.classic;

import com.github.afezeria.freedao.annotation.Dao;
import test.Person;

/**
 *
 */
@Dao(crudEntity = Person.class)
public interface TooManyResultCheckDao {
    Person selectOneByName(String name);
}
