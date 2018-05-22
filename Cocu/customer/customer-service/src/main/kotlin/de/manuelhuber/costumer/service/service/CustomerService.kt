package de.manuelhuber.costumer.service.service

import de.manuelhuber.costumer.service.model.CustomerModel
import de.manuelhuber.costumer.service.model.NotFoundException
import de.manuelhuber.costumer.service.repository.CustomerRepository
import de.manuelhuber.customer.api.Customer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CustomerService {

    @Autowired
    private lateinit var repository: CustomerRepository

    fun update(id: Long, customer: Customer): Customer {
        val old = getModel(id)
        old.birthday = customer.birthday
        old.firstName = customer.firstName
        old.lastName = customer.lastName
        return repository.save(old).toCustomer()

    }

    fun add(customer: Customer): Customer {
        val customerModel = CustomerModel.fromCustomer(customer)
        customerModel.id = 0 // Ensure we save don't override an existing customer
        customerModel.address!!.id = 0
        return repository.save(customerModel).toCustomer()
    }

    fun delete(id: Long) {
        repository.deleteById(id
        )
    }

    fun get(id: Long): Customer {
        return getModel(id).toCustomer()
    }

    fun getModel(id: Long): CustomerModel {
        val model = repository.findById(id)
        if (!model.isPresent) throw NotFoundException("No customer with id=$id found")
        return model.get()
    }

    fun getAll(): Collection<Customer> {
        return repository.findAll().map(CustomerModel::toCustomer)
    }
}
