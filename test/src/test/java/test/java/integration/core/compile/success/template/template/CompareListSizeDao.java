package test.java.integration.core.compile.success.template.template;

import com.github.afezeria.freedao.annotation.Dao;
import com.github.afezeria.freedao.annotation.XmlTemplate;
import test.Person;

import java.util.List;

/**
 * @author afezeria
 */
@Dao
public interface CompareListSizeDao {
    @XmlTemplate("""
            <select>
            select * from person where
            id = <if test='list.size == 0'>1</if>
            </select>
            """)
    List<Person> query(List<String> list);

}