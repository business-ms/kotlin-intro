package at.schmid.example.kotlin

/**
 * Demonstrates object declarations (a.k.a. Singletons), deferred initialization of read-only values, private primary constructors and deferred initialization of
 * non-nullable variables.
 *
 * @author Michael Schmid
 */

object ExpensiveSingleton {

    const val eagerProperty = "value"

    val expensiveProperty by lazy {
        println("Initializing expensive property now...")
        "expensive"
    }
}

class Name private constructor() {

    lateinit var given: String // Allows us to use non-nullable types without initialization. Needs to be initializes before first use, otherwise an
    lateinit var family: String // UninitializedPropertyAccessException will be thrown.

    companion object {

        fun fromString(fullName: String): Name {
            val name = Name()
            val tokens = fullName.split(' ')

            name.given = tokens[0]
            name.family = tokens.elementAtOrElse(1) { "" }
            return name
        }
    }
}

fun main() {
    println(ExpensiveSingleton.eagerProperty)
    println(ExpensiveSingleton.expensiveProperty)

    val name = Name.fromString("Peter Pan")

    println(name.family)
}