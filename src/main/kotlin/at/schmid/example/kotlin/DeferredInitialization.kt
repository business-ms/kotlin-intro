package at.schmid.example.kotlin

/**
 * Demonstrates object declarations (a.k.a. Singletons), deferred initialization of read-only values, deferred initialization of non-nullable variables.
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

class Name {

    lateinit var given: String // Needs to be initializes before first use, otherwise an UninitializedPropertyAccessException will be thrown.
    lateinit var family: String

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
}