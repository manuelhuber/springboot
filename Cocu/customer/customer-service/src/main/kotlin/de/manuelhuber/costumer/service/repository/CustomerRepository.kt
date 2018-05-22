package de.manuelhuber.costumer.service.repository

import de.manuelhuber.costumer.service.model.CustomerModel
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CustomerRepository : CrudRepository<CustomerModel, Long>
