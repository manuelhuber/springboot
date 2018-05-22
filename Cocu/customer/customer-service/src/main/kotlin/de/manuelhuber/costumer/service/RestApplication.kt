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
            repo.save(CustomerModel(1, "Harry", "Hirsch", Date(),
                    AddressModel(123, "Wiesenweg 4", "Rosenheim", 83024)))
            repo.save(CustomerModel(2, "Foo", "Hirsch", Date(),
                    AddressModel(123, "Wiesenweg 4", "Rosenheim", 83024)))
        }
    }
}

fun main(args: Array<String>) {
    runApplication<RestApplication>(*args)
}
