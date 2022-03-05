package test.java.success.core.method.style.named.prefix;

import com.github.afezeria.freedao.annotation.Dao;
import org.jetbrains.annotations.NotNull;
import test.Person;
import test.PersonType;

import java.util.List;

/**
 *
 */
@Dao(crudEntity = Person.class)
public interface QueryByWithParameterTypeHandlerDao {

    List<Person> queryByType(@NotNull PersonType type);
}
