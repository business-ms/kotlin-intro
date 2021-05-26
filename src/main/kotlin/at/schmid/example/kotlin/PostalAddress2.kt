package at.schmid.example.kotlin

import java.io.Serializable

/**
 * Demonstrates data classes, one-line methods, automatic type inference, string interpolation, companion objects and their methods, safe collection access and lambda
 * expressions, package-level variables/constants/methods, named parameters and inheritance.
 *
 * @author Michael Schmid
 */

data class PostalAddress2(
    val streetLine: String,
    val zipCode: String = "6020",
    val city: String = "Innsbruck",
    val countryCode: String? = null) {

    fun format() = "$streetLine\n$zipCode $city\n${countryCode?.toLowerCase()}" // Single-expression method with inferred return type.

    companion object: Serializable {

        // Assumed input: "Fürstenweg 1;6020;Innsbruck;AT
        fun parse(address: String, separator: Char = ';'): PostalAddress2 {
            val tokens = address.split(separator) // This returns an immutable list, not an array. The type of the variable is inferred.

            return PostalAddress2(tokens[0], tokens.elementAtOrElse(1) { "6020" }, tokens.elementAtOrElse(2) {"Innsbruck"}, tokens.elementAtOrNull(3))
        }
    }
}

const val GREETING = "Hello ITH!" // The only real advantage of compile-time constants is that they can be inlined by the compiler.

fun main() {
    println(GREETING)
    println()

    val address = PostalAddress2.parse("Innrain 98;6020")

    println(address) // A custom toString() implementation exists because "PostalAddress2" is a data class.
    println()
    println(address.format())

    val address2 = PostalAddress2(streetLine = "Ballhausplatz 1", city = "Vienna")
}