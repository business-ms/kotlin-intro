package at.schmid.example.kotlin

import java.time.LocalDate

/**
 * Demonstrates more advanced scenarios concerning null-safety, secondary constructors, smart casts, null-safe String extension functions and usage of Java 8 date/time
 * types.
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
    }
}

fun main() {
    val patients = initializePatients()

    for(patient in patients) {
        println(formatPatient(patient))
    }
    for(item in listOf("A String", patients.first(), 23, false, patients.last(), null)) {
        printPatient(item)
    }
}