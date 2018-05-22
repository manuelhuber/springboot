package de.manuelhuber.costumer.service.controller

import de.manuelhuber.costumer.service.model.NotFoundException
import de.manuelhuber.costumer.service.service.CustomerService
import de.manuelhuber.customer.api.Customer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder


@RestController
@RequestMapping(value = ["/customer"], produces = [MediaType.APPLICATION_JSON_VALUE])
@ResponseStatus(HttpStatus.OK)
class CustomerController {

    @Autowired
    private lateinit var service: CustomerService

    @GetMapping()
    fun getAllCustomers(): Collection<Customer> {
        return service.getAllCustomers()
    }

    @GetMapping("/{id}")
    fun getCustomer(@PathVariable id: Long): Customer {
        return service.getCustomer(id)
    }

    @PutMapping("/{id}")
    fun updateCustomer(@PathVariable id: Long, @RequestBody customerPojo: Customer): Customer {
        return service.update(id, customerPojo)
    }

    @PostMapping()
    fun addCustomer(@RequestBody customerPojo: Customer, uriBuilder: UriComponentsBuilder): ResponseEntity<Customer> {
        val customer = service.add(customerPojo)
        val headers = HttpHeaders()
        headers.location = uriBuilder.path("/customer/{id}").buildAndExpand(customer.id).toUri()
        return ResponseEntity(customer, headers, HttpStatus.CREATED)
    }

    @DeleteMapping("/{id}")
    fun deleteCustomer(@PathVariable id: Long) {
        service.delete(id)
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException::class)
    fun notFound(exception: NotFoundException): String {
        println(exception.message)
        return exception.message
    }

}
