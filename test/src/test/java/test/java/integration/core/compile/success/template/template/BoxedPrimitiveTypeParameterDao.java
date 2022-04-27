package test.java.integration.core.compile.success.template.template;

import io.github.afezeria.freedao.annotation.Dao;
import io.github.afezeria.freedao.annotation.XmlTemplate;
import test.Person;

import java.util.List;

/**
 * @author afezeria
 */
@Dao
public interface BoxedPrimitiveTypeParameterDao {
    @XmlTemplate("""
            <select>
            select * from person where
            id = <if test='a == 0'>1</if>
            </select>
            """)
    List<Person> query(int a);

}
