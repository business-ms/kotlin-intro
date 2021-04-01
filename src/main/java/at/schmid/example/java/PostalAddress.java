package at.schmid.example.java;

import java.util.Objects;

/**
 * Demonstrates the equivalent Java code that is necessary to write a simple model class.
 *
 * @author Michael Schmid
 */
public final class PostalAddress {

    private final String streetLine;
    private final String zipCode;
    private final String city;
    private final String countryCode;

    /* Due to constructor overloading limitations we cannot even represent all possible instantiation scenarios in Java. */
    public PostalAddress(String streetLine) {
        this(streetLine, "6020", "Innsbruck", null);
    }

    public PostalAddress(String streetLine, String zipCode) {
        this(streetLine, zipCode, "Innsbruck", null);
    }

    public PostalAddress(String streetLine, String zipCode, String city) {
        this(streetLine, zipCode, city, null);
    }

    public PostalAddress(String streetLine, String zipCode, String city, String countryCode) {
        /* Null-checks are actually done using special validation annotations, e.g. from JSR-305. */
        Objects.requireNonNull(streetLine);
        Objects.requireNonNull(zipCode);
        Objects.requireNonNull(city);
        this.streetLine = streetLine;
        this.zipCode = zipCode;
        this.city = city;
        this.countryCode = countryCode;
    }

    public String getStreetLine() {
        return streetLine;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getCity() {
        return city;
    }

    public String getCountryCode() {
        return countryCode;
    }
}