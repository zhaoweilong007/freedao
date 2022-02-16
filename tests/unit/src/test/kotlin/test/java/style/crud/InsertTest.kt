package test.java.style.crud

import org.junit.Test
import test.BaseTest
import test.Person
import test.java.style.crud.insert.PersonInsertDao
import test.java.style.crud.insert.PersonInsertSelectiveDao

/**
 *
 */
class InsertTest : BaseTest() {
    @Test
    fun `insert all fields`() {
        initData<Person>()
        val impl = getJavaDaoInstance<PersonInsertDao>()
        val entity = Person(name = "a")
        val updateCount = impl.insert(entity)
        assert(updateCount == 1)
        assert(entity.id != null)
        env.find("person", "id = ${entity.id}")[0].let {
            assert(it["when_created"] == null)
        }
    }

    @Test
    fun `insert non-null fields`() {
        val impl = getJavaDaoInstance<PersonInsertSelectiveDao>()
        val entity = Person(name = "a")
        val updateCount = impl.insertSelective(entity)
        assert(updateCount == 1)
        assert(entity.id != null)
        env.find("person", "id = ${entity.id}")[0].let {
            assert(it["when_created"] != null)
        }
    }
}