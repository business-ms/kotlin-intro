package at.schmid.example.kotlin

import java.time.LocalDate
import java.util.*

/**
 * Simple demonstration of extension functions.
 *
 * @author Michael Schmid
 */

fun Calendar.toLocalDate(): LocalDate {
    return LocalDate.of(get(Calendar.YEAR), get(Calendar.MONTH), get(Calendar.DAY_OF_MONTH))
}

fun main() {
    val calendar = Calendar.getInstance()

    println(calendar.toLocalDate())
    // Static extension functions are only possible on Companion objects, but not on existing Java objects: https://youtrack.jetbrains.com/issue/KT-11968
}