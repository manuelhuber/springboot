package de.manuelhuber.costumer.service

import de.manuelhuber.costumer.service.model.AddressModel
import de.manuelhuber.costumer.service.model.CustomerModel
import de.manuelhuber.costumer.service.repository.CustomerRepository
import org.springframework.beans.factory.InitializingBean
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import java.util.*

@SpringBootApplication
class RestApplication {
    @Bean
    fun inizialisiere(repo: CustomerRepository): InitializingBean {
        return InitializingBean {
            repo.save(CustomerModel("Harry", "Hirsch", Date(),
                    AddressModel("Wiesenweg 4", "Rosenheim", 83024)))
        }
    }
}

fun main(args: Array<String>) {
    runApplication<RestApplication>(*args)
}
