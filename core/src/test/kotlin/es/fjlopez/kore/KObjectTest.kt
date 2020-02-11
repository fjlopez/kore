package es.fjlopez.kore

import kotlin.reflect.KMutableProperty1
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith


class KObjectTest {

    data class Sut(var varProperty: Int,
                   val valProperty: Int)

    lateinit var sut: Sut

    @BeforeTest
    fun before() {
        sut = Sut(3, 4)
    }

    @Test
    fun `returns the value of a given feature of an object by its name`() {
        val result: Int = sut["varProperty"]
        assertEquals(3, result)
    }

    @Test
    fun `fails if the value is not compatible with the expression`() {
        assertFailsWith<ClassCastException> {
            @Suppress("UNUSED_VARIABLE")
            val result: String = sut["varProperty"]
        }
    }

    @Test
    fun `fails if the feature is not known`() {
        assertFailsWith<IllegalArgumentException> {
            @Suppress("UNUSED_VARIABLE")
            val result: String = sut["otherFeature"]
        }
    }

    @Test
    fun `set fail because the feature is not changeable`() {
        assertFailsWith<IllegalArgumentException> {
            sut["valProperty"] = 5
        }
    }

    @Test
    fun `set works because the feature is  changeable`() {
        sut["varProperty"] = 5
        assertEquals(5, sut["varProperty"])
    }


    @Test
    fun `set fail because the feature type is not valid`() {
        assertFailsWith<IllegalArgumentException> {
            sut["varProperty"] = "5"
        }
    }
}
