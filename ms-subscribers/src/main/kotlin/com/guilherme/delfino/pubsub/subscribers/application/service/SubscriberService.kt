package com.guilherme.delfino.pubsub.subscribers.application.service

import com.guilherme.delfino.pubsub.subscribers.domain.entity.Subscriber

interface SubscriberService {
    fun create(subscriber: com.guilherme.delfino.pubsub.subscribers.domain.entity.Subscriber): com.guilherme.delfino.pubsub.subscribers.domain.entity.Subscriber
    fun getAll(): List<com.guilherme.delfino.pubsub.subscribers.domain.entity.Subscriber>
    fun getById(id: String): com.guilherme.delfino.pubsub.subscribers.domain.entity.Subscriber
}