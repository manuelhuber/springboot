package de.manuelhuber.customer.api

class Address() {
    var id: Long = 0
    lateinit var street: String
    lateinit var city: String
    var zipCode: Int = 0

    constructor(id: Long,
                street: String,
                city: String,
                zipCode: Int) : this() {
        this.id = id
        this.street = street
        this.city = city
        this.zipCode = zipCode
    }

}
