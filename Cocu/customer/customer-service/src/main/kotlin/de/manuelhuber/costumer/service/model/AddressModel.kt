package de.manuelhuber.costumer.service.model

import de.manuelhuber.customer.api.Address
import javax.persistence.*

@Entity
@Table(name = "addresses")
class AddressModel(@Id
                   @GeneratedValue(strategy = GenerationType.IDENTITY)
                   var id: Long = 0,
                   var street: String = "",
                   var city: String = "",
                   var zipCode: Int = 0
) {

    fun toAddress(): Address {
        return Address(id, street, city, zipCode)
    }

    companion object {
        fun fromAddress(address: Address): AddressModel {
            val model = AddressModel()
            model.id = address.id
            model.street = address.street
            model.city = address.city
            model.zipCode = address.zipCode
            return model
        }
    }
}
