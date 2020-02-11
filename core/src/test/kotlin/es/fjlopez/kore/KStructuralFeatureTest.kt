package es.fjlopez.kore

import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue


class KStructuralFeatureTest {

    data class Sut(
        var varProperty: Int,
        val valProperty: Int
    )

    lateinit var sut: Sut

    @BeforeTest
    fun before() {
        sut = Sut(3, 4)
    }

    @Test
    fun `var property is changeable`() {
        val feature = sut.findFeatureByName("varProperty")
        assertTrue(feature.changeable)
    }

    @Test
    fun `val property is not changeable`() {
        val feature = sut.findFeatureByName("valProperty")
        assertFalse(feature.changeable)
    }
}

