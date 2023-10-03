package com.guilherme.delfino.pubsub.subscribers.domain.exceptions

class EntityNotFound(message: String = "Entity Not Found") : IllegalArgumentException(message) {
}