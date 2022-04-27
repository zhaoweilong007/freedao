package test.java.integration.core.compile.failure.method.style.named.prefix;

import io.github.afezeria.freedao.annotation.Dao;
import org.jetbrains.annotations.NotNull;
import test.Person;

import java.util.List;

/**
 *
 */
@Dao(crudEntity = Person.class)
public interface QueryNotReturnEntityCollectionBadDao {

    List<String> queryByName(@NotNull String name);
}
