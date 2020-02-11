package es.fjlopez.kore

import kotlin.reflect.KMutableProperty1
import kotlin.reflect.KProperty1

/**
 * A representation of a structural feature.
 */
typealias KStructuralFeature = KProperty1<out KObject, Any?>

/**
 * Returns true if the structural feature is a mutable property.
 */
val KStructuralFeature.changeable: Boolean
    get() = this is KMutableProperty1

/**
 * Returns this structural feature as mutable property if it is changeable or
 * throws and [IllegalArgumentException].
 */
fun KStructuralFeature.asMutableProperty() : KMutableProperty1<KObject, Any?> {
    if (!changeable) {
        throw IllegalArgumentException("The feature [$name] is not changeable")
    }
    @Suppress("UNCHECKED_CAST")
    return this as KMutableProperty1<KObject, Any?>
}