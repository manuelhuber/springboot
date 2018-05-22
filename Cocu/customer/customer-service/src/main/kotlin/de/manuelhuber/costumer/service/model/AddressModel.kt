package de.manuelhuber.costumer.service.model

import de.manuelhuber.customer.api.Address
import javax.persistence.*

@Entity
@Table(name = "addresses")
class AddressModel(
        val street: String,
        val city: String,
        val zipCode: Int
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0

    fun toAddress(): Address {
        return Address(street, city, zipCode)
    }

    companion object {
        fun fromAddress(address: Address): AddressModel {
            return AddressModel(address.street, address.city, address.zipCode)
        }
    }
}
