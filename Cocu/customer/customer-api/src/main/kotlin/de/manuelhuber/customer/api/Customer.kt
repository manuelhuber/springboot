package de.manuelhuber.customer.api

public class Customer(public val name: String) {

    fun doStuff(): String {
        return name
    }
}
