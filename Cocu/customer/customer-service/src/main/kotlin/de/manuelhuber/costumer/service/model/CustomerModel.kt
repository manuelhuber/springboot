package de.manuelhuber.costumer.service.model

import de.manuelhuber.customer.api.Customer
import java.util.*
import javax.persistence.*


@Entity
@Table(name = "customers")
class CustomerModel(
        val firstName: String,
        val lastName: String,
        val birthday: Date,
        @OneToOne(fetch = FetchType.EAGER,
                cascade = [CascadeType.ALL])
        val address: AddressModel
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0

    fun toCustomer(): Customer {
        return Customer(id, firstName, lastName, birthday, address.toAddress())
    }

    companion object {
        fun fromCustomer(customer: Customer): CustomerModel {
            return CustomerModel(
                    customer.firstName,
                    customer.lastName,
                    customer.birthday,
                    AddressModel.fromAddress(customer.address)
            )
        }
    }
}
