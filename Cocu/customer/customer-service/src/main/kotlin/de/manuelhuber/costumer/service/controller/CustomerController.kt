package de.manuelhuber.costumer.service.controller

import de.manuelhuber.costumer.service.model.CustomerModel
import de.manuelhuber.costumer.service.repository.CustomerRepository
import de.manuelhuber.customer.api.Customer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder


@RestController
@RequestMapping(value = ["/customer"])
class CustomerController {

    @Autowired
    private lateinit var repository: CustomerRepository

    @RequestMapping(method = [(RequestMethod.GET)], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllCustomers(): ResponseEntity<List<Customer>> {
        val customers: List<Customer> = repository.findAll().map(CustomerModel::toCustomer)
        return ResponseEntity(customers, HttpStatus.OK)
    }

    @RequestMapping(value = ["/{id}"], method = [(RequestMethod.GET)], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getCustomer(@PathVariable id: Long): ResponseEntity<Customer> {
        val model = repository.findById(id)
        if (!model.isPresent) return ResponseEntity(HttpStatus.NOT_FOUND)
        return ResponseEntity(model.get().toCustomer(), HttpStatus.OK)
    }

    @RequestMapping(method = [(RequestMethod.POST)], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun addCustomer(@RequestBody customerPojo: Customer, uriBuilder: UriComponentsBuilder): ResponseEntity<Customer> {
        val customer = repository.save(CustomerModel.fromCustomer(customerPojo))
        customer.id = 0 // Ensure we save don't override an existing customer
        customer.address!!.id = 0
        val headers = HttpHeaders()
        headers.location = uriBuilder.path("/customer/{id}").buildAndExpand(customer.id).toUri()
        return ResponseEntity(headers, HttpStatus.CREATED)
    }

    @RequestMapping(value = ["/{id}"], method = [(RequestMethod.DELETE)], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun deleteCustomer(@PathVariable id: Long): ResponseEntity<Unit> {
        repository.deleteById(id)
        return ResponseEntity(HttpStatus.OK)
    }
}
