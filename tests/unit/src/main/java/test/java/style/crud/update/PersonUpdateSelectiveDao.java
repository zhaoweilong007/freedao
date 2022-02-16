package test.java.style.crud.update;

import com.github.afezeria.freedao.annotation.Dao;
import org.jetbrains.annotations.NotNull;
import test.Person;

/**
 */
@Dao(crudEntity = Person.class)
public interface PersonUpdateSelectiveDao {

    int updateSelective(@NotNull Person entity);
}