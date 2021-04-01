package at.schmid.example.kotlin

/**
 * Demonstrates simple model class definitions, the "public by default" visibility, read-only properties, non-nullable types, the primary constructor and constructor
 * default arguments.
 *
 * @author Michael Schmid
 */
class PostalAddress(
    val streetLine: String,
    val zipCode: String = "6020",
    val city: String = "Innsbruck",
    val countryCode: String? = null)