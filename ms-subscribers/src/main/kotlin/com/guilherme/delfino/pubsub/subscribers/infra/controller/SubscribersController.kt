package com.guilherme.delfino.pubsub.subscribers.infra.controller

import com.guilherme.delfino.pubsub.subscribers.domain.entity.Subscriber
import com.guilherme.delfino.pubsub.subscribers.domain.entity.vo.SubscriberEmail
import com.guilherme.delfino.pubsub.subscribers.domain.entity.vo.SubscriberName
import com.guilherme.delfino.pubsub.subscribers.infra.controller.dto.CreateSubscriberDto
import com.guilherme.delfino.pubsub.subscribers.infra.service.SubscriberServiceSpring
import jakarta.validation.Valid
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.security.access.annotation.Secured
import org.springframework.stereotype.Controller
import org.springframework.validation.annotation.Validated
import java.util.UUID

@Controller
class SubscribersController(private val service: SubscriberServiceSpring) {
    private val log: Logger = LoggerFactory.getLogger(SubscribersController::class.java)

    @QueryMapping(name = "findById")
    @Secured("ROLE_USER")
    fun getBydId(@Argument id: UUID): Subscriber {
        log.info("Finding Subscribers with ID {} ", id)
        return service.getById(id.toString());
    }
    @QueryMapping
    @Secured("ROLE_USER")
    fun findAll(): List<Subscriber> {
        log.info("Finding all Subscribers . . .")
        return service.getAll();
    }

    @MutationMapping
    @Validated
    @Secured("ROLE_ADMIN")
    fun createSubscriber(@Valid @Argument("input")  dto: CreateSubscriberDto): Subscriber {
        log.info("Creating Subscriber with input: {} ", dto)
        return service.create(
            Subscriber(
                SubscriberName.of(dto.name),
                SubscriberEmail.of(dto.email)
            )
        )
    }


}