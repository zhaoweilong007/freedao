package test.java.integration.core.compile.success.method.style.xml.simple;

import io.github.afezeria.freedao.annotation.Dao;
import io.github.afezeria.freedao.annotation.XmlTemplate;
import org.jetbrains.annotations.NotNull;
import test.Person;

import java.util.List;

/**
 * @author afezeria
 */
@Dao
public interface InNodeWithItemAttrDao {
    @NotNull
    @XmlTemplate("""
            <select>
            select * from person
            where id in
            <In collection='list' item='id'/>
            </select>
            """)
    List<Person> queryInEntity(List<Person> list);

}