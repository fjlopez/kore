package es.fjlopez.kore

import kotlin.reflect.full.memberProperties

/**
 * A representation of an object.
 */
typealias KObject = Any

/**
 * Returns the value in this object of a feature with such [featureName] or
 * throws an [IllegalArgumentException] if the feature is not found or
 * throws a [ClassCastException] if the value cannot be cast.
 */
inline operator fun <reified T> KObject.get(featureName: String): T =
    findFeatureByName(featureName)
        .call(this) as T

/**
 * Sets the value in this object of a feature with such [featureName] or
 * throws an [IllegalArgumentException] if the feature is not found or it is not changeable or
 * throws a [ClassCastException] if the value cannot be cast.
 */
operator fun <T: Any?> KObject.set(featureName: String, value: T) {
    findFeatureByName(featureName).asMutableProperty().set(this, value)
}

/**
 * Returns the feature with such [name] of this object or
 * throws an [IllegalArgumentException] if the feature is not found.
 */
fun KObject.findFeatureByName(name: String): KStructuralFeature =
    this::class.memberProperties
        .find { it.name == name }
        ?: throw IllegalArgumentException("The feature [$name] is not one of the object features")
