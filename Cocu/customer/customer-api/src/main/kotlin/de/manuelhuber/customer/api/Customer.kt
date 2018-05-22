package de.manuelhuber.customer.api

import java.util.*

public class Customer(
        val id: Long,
        val firstName: String,
        val lastName: String,
        val birthday: Date,
        val address: Address
)
