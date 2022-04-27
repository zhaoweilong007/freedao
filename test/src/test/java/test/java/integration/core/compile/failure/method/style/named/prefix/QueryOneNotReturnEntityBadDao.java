package test.java.integration.core.compile.failure.method.style.named.prefix;

import io.github.afezeria.freedao.annotation.Dao;
import org.jetbrains.annotations.NotNull;
import test.Person;

/**
 *
 */
@Dao(crudEntity = Person.class)
public interface QueryOneNotReturnEntityBadDao {

    String queryOneByName(@NotNull String name);
}
