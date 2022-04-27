package test.java.integration.core.compile.success.method.style.named.order;

import io.github.afezeria.freedao.annotation.Dao;
import org.jetbrains.annotations.NotNull;
import test.Person;

import java.util.List;

/**
 * @author afezeria
 */
@Dao(crudEntity = Person.class)
public interface OrderWithMultipleColumnDao {

    @NotNull
    List<Person> queryByWhenCreatedNotNullOrderByNameAscIdAsc();

    @NotNull
    List<Person> queryByIdNotNullOrderByNameAscIdDesc();
}
