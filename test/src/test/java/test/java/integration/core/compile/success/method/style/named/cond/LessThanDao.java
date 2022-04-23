package test.java.integration.core.compile.success.method.style.named.cond;

import com.github.afezeria.freedao.annotation.Dao;
import org.jetbrains.annotations.NotNull;
import test.Person;

import java.util.List;

/**
 * @author afezeria
 */
@Dao(crudEntity = Person.class)
public interface LessThanDao {

    @NotNull
    List<Person> queryByIdLessThan(Long i);

}