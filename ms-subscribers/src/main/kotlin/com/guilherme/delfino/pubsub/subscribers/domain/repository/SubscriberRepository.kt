package com.guilherme.delfino.pubsub.subscribers.domain.repository

import com.guilherme.delfino.pubsub.subscribers.domain.entity.Subscriber
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface SubscriberRepository {
    fun save(subscriber: Subscriber): Subscriber
    fun findAll(): List<Subscriber>
    fun findById(id: UUID): Subscriber
}