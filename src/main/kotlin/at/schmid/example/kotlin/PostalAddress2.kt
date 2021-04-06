package at.schmid.example.kotlin

import java.util.function.BiFunction

/**
 * Demonstrates data classes, one-line methods with return type inference, string interpolation, companion objects and their methods, safe collection access and lambda
 * expressions, top level variables/constants/methods, named parameters and inheritance.
 *
 * @author Michael Schmid
 */

data class PostalAddress2(
    val streetLine: String,
    val zipCode: String = "6020",
    val city: String = "Innsbruck",
    val countryCode: String? = null) {

    fun format() = "$streetLine\n$zipCode $city\n${countryCode?.toLowerCase()}"

    companion object: BiFunction<String, Char, PostalAddress2> {

        // Assumed input: "Fürstenweg 1;6020;Innsbruck;AT
        fun parse(address: String, separator: Char = ';'): PostalAddress2 {
            val tokens = address.split(separator) // This is an immutable list, not an array.

            return PostalAddress2(tokens[0], tokens.elementAtOrElse(1) { "6020" }, tokens.elementAtOrElse(2) {"Innsbruck"}, tokens.elementAtOrNull(3))
        }

        override fun apply(address: String, separator: Char) = parse(address, separator)
    }
}

const val GREETING = "Hello ITH!"

fun main() {
    println(GREETING)

    val address = PostalAddress2.parse("Innrain 98;6020")

    println(address)
    println(address.format())

    val address2 = PostalAddress2(streetLine = "Ballhausplatz 1", city = "Vienna")
}