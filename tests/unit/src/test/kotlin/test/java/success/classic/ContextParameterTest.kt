package test.java.success.classic

import com.github.afezeria.freedao.runtime.classic.context.DaoContextParameterUtil
import com.github.afezeria.freedao.runtime.classic.context.ExecutorContext
import com.github.afezeria.freedao.runtime.classic.context.ParameterContext
import com.github.afezeria.freedao.runtime.classic.context.TransactionContext
import org.junit.Test
import test.BaseTest
import test.Person
import test.java.success.classic.contextparameter.DynamicTableNameDao
import test.java.success.classic.contextparameter.DynamicTableNameEntity
import test.java.success.classic.contextparameter.TempContextParameterDao
import java.util.function.Supplier

/**
 *
 * @author afezeria
 */
class ContextParameterTest : BaseTest() {
    @Test
    fun dynamicTableName() {
        initData(DynamicTableNameEntity().setName("a"))
        env.withContext({
            ParameterContext(ExecutorContext(TransactionContext(dataSource)), mapOf("year" to 2000))
        }) {
            val impl = getJavaDaoInstance<DynamicTableNameDao>()
            val entity = impl.selectOneById(1)
            assert(entity.name == "a")
        }

        env.withContext({
            ParameterContext(ExecutorContext(TransactionContext(dataSource)), mapOf("year" to Supplier { 2000 }))
        }) {
            val impl = getJavaDaoInstance<DynamicTableNameDao>()
            val entity = impl.selectOneById(1)
            assert(entity.name == "a")
        }
    }

    @Test
    fun tempContextParameter() {
        initData(Person(name = "a"), Person(name = "b"))
        val impl = getJavaDaoInstance<TempContextParameterDao>()
        val entity = DaoContextParameterUtil.with("id", 1L) {
            impl.select()
        }
        assert(entity.name == "a")
        val select = impl.select()
        assert(select == null)
    }
}