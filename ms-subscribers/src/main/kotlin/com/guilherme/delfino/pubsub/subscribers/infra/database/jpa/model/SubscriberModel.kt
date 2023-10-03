package com.guilherme.delfino.pubsub.subscribers.infra.database.jpa.model

import jakarta.persistence.*
import org.hibernate.Hibernate
import java.time.LocalDate
import java.util.UUID

@Entity
@Table(name = "subscribers")
data class SubscriberModel(
    @Id val id: UUID,
    val name: String = "",
    val email: String = "",
    val subscribeDate: LocalDate = LocalDate.now()
){
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as SubscriberModel

        return id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id , name = $name , subscribeDate = $subscribeDate )"
    }
}
