package at.schmid.example.kotlin

import java.time.LocalDate
import java.time.format.DateTimeParseException

/**
 * Demonstrates enumerations and various expressions in Kotlin, specifically the fact that all functions (even those returning Unit) are expressions. Additionally
 * demonstrates scope functions, collections and mutability and destructuring expressions.
 *
 * @author Michael Schmid
 */

enum class StreamingProvider {

    NETFLIX,
    AMAZON,
    DISNEY
}

fun chooseStreamingProvider(favoriteCategory: String): StreamingProvider? {
    return when(favoriteCategory) {
        "series" -> StreamingProvider.NETFLIX
        "movies" -> StreamingProvider.AMAZON
        "cartoons", "star wars" -> StreamingProvider.DISNEY
        else -> null
    }
}

fun printChoiceWithFallback(category: String) {
    val choice = chooseStreamingProvider(category) ?: StreamingProvider.NETFLIX

    println("You like $category, you should choose $choice.")
}

fun printPositiveNumbers(numbers: List<Int>) {
    numbers.filter { it > 0 }
        .sorted()
        .map { ">>> $it <<<" }
        .forEach { println(it) }
}

fun printCategoriesByProvider() {
    val categoriesByProvider = mutableMapOf(StreamingProvider.NETFLIX to "series", StreamingProvider.AMAZON to "movies", StreamingProvider.DISNEY to "cartoons")

    for((provider, category) in categoriesByProvider) {
        println("$provider offers $category")
    }
}

fun parseAndPrintDate(dateString: String) {
    val parsedDate = try {
        LocalDate.parse(dateString)
    } catch (exception: DateTimeParseException) {
        println("'$dateString' is not a valid date!")
        return
    }

    println(parsedDate.year)
}

fun main() {
    val categories = listOf("star wars", "series", "car shows", "movies")

    for(category in categories) {
        chooseStreamingProvider(category)?.let {
            println("You like $category, you should choose $it.")
        }
    }
    categories.forEach { printChoiceWithFallback(it) }

    val numbers = mutableListOf(0, -12, 23, 17, -5, 1)

    numbers.add(34)
    printPositiveNumbers(numbers)
    printCategoriesByProvider()
    parseAndPrintDate("2021-04-01")
    parseAndPrintDate("Not a date")
}