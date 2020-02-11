package es.fjlopez.kore

import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith


class KObjectTest {

    data class Sut(var intFeature: Int)

    lateinit var sut: Sut

    @BeforeTest
    fun before() {
        sut = Sut(3)
    }

    @Test
    fun `returns the value of a given feature of an object by its name`() {
        val result: Int = sut["intFeature"]
        assertEquals(3, result)
    }

    @Test
    fun `fails if the value is not compatible with the expression`() {
        assertFailsWith<ClassCastException> {
            @Suppress("UNUSED_VARIABLE")
            val result: String = sut["intFeature"]
        }
    }

    @Test
    fun `fails if the feature is not known`() {
        assertFailsWith<IllegalArgumentException> {
            @Suppress("UNUSED_VARIABLE")
            val result: String = sut["otherFeature"]
        }
    }
}
