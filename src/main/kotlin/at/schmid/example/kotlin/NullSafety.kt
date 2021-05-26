package at.schmid.example.kotlin

import java.time.LocalDate

/**
 * Demonstrates more advanced scenarios concerning null-safety, secondary constructors, smart casts and null-safe String extension functions.
 *
 * @author Michael Schmid
 */

class PatientName(var firstName: String? = null, var lastName: String? = null)

class Patient() {

    var name: PatientName? = null
    var gender: String? = "unknown"
    var dateOfBirth: LocalDate? = null

    constructor(name: PatientName?): this() {
        this.name = name
        this.dateOfBirth = LocalDate.now()
    }
}

fun formatPatient(patient: Patient?): String {
    if(patient == null) {
        return "Null patient"
    }
    // The null check smart-casts "Patient?" to "Patient"
    return "${patient.name?.firstName.orEmpty()} ${patient.name?.lastName.orEmpty()}"
}

fun initializePatients(): List<Patient?> {
    val patient1 = Patient(PatientName("Sherlock"))
    val patient2 = Patient(PatientName("Mycroft", "Holmes"))

    return listOf(patient1, null, patient2)
}

fun printPatient(any: Any?) {
    if(any is Patient) {
        println(formatPatient(any)) // Type and null checks both result in a smart cast.
        println()
    }
}

fun printWithScopeFunction(list: List<Any?>) {
    for(item in list) {
        item?.let {
            /* "it" is the implicit lambda argument of all scope functions and single-element lambda expressions. */
            if(it is Patient) {
                println(formatPatient(it))
            } else {
                println(it)
            }
        }
    }
}

fun main() {
    val patients = initializePatients()

    for(patient in patients) {
        println(formatPatient(patient))
        println()
    }
    println("Output with a heterogeneous list:\n")

    val list = listOf("A String", patients.first(), 23, false, patients.last(), null)

    for(item in list) {
        printPatient(item)
    }
    println("Output filtering with scope function:\n")
    printWithScopeFunction(list)
}