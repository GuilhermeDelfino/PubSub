package com.guilherme.delfino.pubsub.subscribers.infra.database.jpa.mapper

import com.guilherme.delfino.pubsub.subscribers.domain.entity.vo.SubscriberEmail
import com.guilherme.delfino.pubsub.subscribers.domain.entity.vo.SubscriberName
import com.guilherme.delfino.pubsub.subscribers.infra.database.jpa.model.SubscriberModel

object SubscriberJpaMapper {
    fun modelToEntity(model: SubscriberModel): com.guilherme.delfino.pubsub.subscribers.domain.entity.Subscriber {
        return com.guilherme.delfino.pubsub.subscribers.domain.entity.Subscriber(
            id = model.id,
            name = SubscriberName.of(model.name),
            email = SubscriberEmail.of(model.email),
            subscribeDate = model.subscribeDate
        )
    }
    fun entityToModel(entity: com.guilherme.delfino.pubsub.subscribers.domain.entity.Subscriber): SubscriberModel{
        return SubscriberModel(entity.id, entity.name.toString(), entity.email.toString(), entity.subscribeDate)
    }
}