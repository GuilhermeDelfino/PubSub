package com.guilherme.delfino.pubsub.subscribers.infra.controller.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class CreateSubscriberDto (@get:NotBlank @get:Size(min = 3, max = 150) val name: String, @get:NotBlank @get:Email val email: String)
