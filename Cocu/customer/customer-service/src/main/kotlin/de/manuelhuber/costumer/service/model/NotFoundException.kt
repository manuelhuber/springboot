package de.manuelhuber.costumer.service.model

class NotFoundException(override var message: String) : Exception(message)
