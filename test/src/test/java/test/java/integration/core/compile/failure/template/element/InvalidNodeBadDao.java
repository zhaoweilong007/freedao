package test.java.integration.core.compile.failure.template.element;

import com.github.afezeria.freedao.annotation.Dao;
import com.github.afezeria.freedao.annotation.XmlTemplate;

import java.util.List;

/**
 * @author afezeria
 */
@Dao
public interface InvalidNodeBadDao {
    @XmlTemplate("""
            <select>
            <abc></abc>
            </select>
            """)
    List query();
}