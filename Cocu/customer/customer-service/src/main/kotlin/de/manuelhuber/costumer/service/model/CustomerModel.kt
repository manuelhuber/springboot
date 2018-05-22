package de.manuelhuber.costumer.service.model

import de.manuelhuber.customer.api.Customer
import java.util.*
import javax.persistence.*


@Entity
@Table(name = "customers")
class CustomerModel(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long = 0,
        var firstName: String = "",
        var lastName: String = "",
        var birthday: Date = Date(),
        @OneToOne(fetch = FetchType.EAGER,
                cascade = [CascadeType.ALL])
        var address: AddressModel? = null
) {


    fun toCustomer(): Customer {
        return Customer(id, firstName, lastName, birthday, address!!.toAddress())
    }

    companion object {
        fun fromCustomer(customer: Customer): CustomerModel {
            val model = CustomerModel()
            model.id = customer.id
            model.firstName = customer.firstName
            model.lastName = customer.lastName
            model.birthday = customer.birthday
            model.address = AddressModel.fromAddress(customer.address)
            return model
        }
    }
}
