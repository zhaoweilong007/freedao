package test.java.failure.core.method.style.crud;

import com.github.afezeria.freedao.annotation.Dao;
import test.Person;

import java.util.List;

/**
 * @author afezeria
 */
@Dao
public interface NotSpecifyCrudEntityBadDao {
    List<Person> list();
}