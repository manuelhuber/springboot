package de.manuelhuber.customer.api

import java.util.*

class Customer() {
    var id: Long = 0
    lateinit var firstName: String
    lateinit var lastName: String
    lateinit var birthday: Date
    lateinit var address: Address

    constructor(id: Long,
                firstName: String,
                lastName: String,
                birthday: Date,
                address: Address) : this() {
        this.id = id
        this.firstName = firstName
        this.lastName = lastName
        this.birthday = birthday
        this.address = address
    }
}
