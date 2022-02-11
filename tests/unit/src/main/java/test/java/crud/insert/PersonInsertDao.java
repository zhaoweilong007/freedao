package test.java.crud.insert;

import com.github.afezeria.freedao.annotation.Dao;
import org.jetbrains.annotations.NotNull;
import test.Person;

/**
 */
@Dao(crudEntity = Person.class)
public interface PersonInsertDao {

    int insert(@NotNull Person entity);
}
