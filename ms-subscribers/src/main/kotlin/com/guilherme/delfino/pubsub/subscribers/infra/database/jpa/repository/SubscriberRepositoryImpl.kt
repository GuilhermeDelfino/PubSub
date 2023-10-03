package com.guilherme.delfino.pubsub.subscribers.infra.database.jpa.repository

import com.guilherme.delfino.pubsub.subscribers.domain.entity.Subscriber
import com.guilherme.delfino.pubsub.subscribers.domain.exceptions.EntityNotFound
import com.guilherme.delfino.pubsub.subscribers.domain.repository.SubscriberRepository
import com.guilherme.delfino.pubsub.subscribers.infra.database.jpa.mapper.SubscriberJpaMapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class SubscriberRepositoryImpl(private val repository: JpaSubscriberRepository): SubscriberRepository {

    val logger: Logger = LoggerFactory.getLogger(SubscriberRepositoryImpl::class.java)
    override fun save(subscriber: Subscriber): Subscriber {
        val model = SubscriberJpaMapper.entityToModel(subscriber)
        val savedModel = repository.save(model)
        return SubscriberJpaMapper.modelToEntity(savedModel)
    }

    override fun findAll(): List<Subscriber> {
        logger.info("Searching for all subscribers")
        val findAll = repository.findAll()
        logger.info("{} subscribers found", findAll.size)
        return findAll.map(SubscriberJpaMapper::modelToEntity);
    }

    override fun findById(id: UUID): Subscriber {
        val subscriber = repository.findById(id).orElseThrow { throw EntityNotFound("Subscriber Not Found") }
        return SubscriberJpaMapper.modelToEntity(subscriber);
    }
}