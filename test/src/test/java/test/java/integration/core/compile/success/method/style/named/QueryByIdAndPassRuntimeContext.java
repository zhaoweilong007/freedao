package test.java.integration.core.compile.success.method.style.named;

import io.github.afezeria.freedao.annotation.Dao;
import test.Person;

import java.util.List;
import java.util.Map;

/**
 * @author afezeria
 */
@Dao(crudEntity = Person.class)
public interface QueryByIdAndPassRuntimeContext {
    List<Person> queryById(Map<String, Object> context, Long id);
}
