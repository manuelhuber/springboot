package de.manuelhuber.costumer.service.controller

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class CustomerController {
    @RequestMapping(value = ["foo"])
    fun foo(): String {
        return "foo"
    }
}
