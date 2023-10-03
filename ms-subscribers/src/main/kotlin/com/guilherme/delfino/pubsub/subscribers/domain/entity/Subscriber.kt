package com.guilherme.delfino.pubsub.subscribers.domain.entity

import com.guilherme.delfino.pubsub.subscribers.domain.entity.vo.SubscriberEmail
import com.guilherme.delfino.pubsub.subscribers.domain.entity.vo.SubscriberName
import java.io.Serializable
import java.time.LocalDate
import java.util.UUID

class Subscriber(
    val name: SubscriberName,
    val email: SubscriberEmail,
    val id: UUID = UUID.randomUUID(),
    val subscribeDate: LocalDate = LocalDate.now()
    ) : Serializable {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as com.guilherme.delfino.pubsub.subscribers.domain.entity.Subscriber

        if (name.toString() != other.name.toString()) return false
        if (email.toString() != other.email.toString()) return false
        if (id != other.id) return false
        if (subscribeDate != other.subscribeDate) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + email.hashCode()
        result = 31 * result + id.hashCode()
        result = 31 * result + subscribeDate.hashCode()
        return result
    }

    override fun toString(): String {
        return "Subscriber(name=$name, email=$email, id=$id, subscribeDate=$subscribeDate)"
    }
}
