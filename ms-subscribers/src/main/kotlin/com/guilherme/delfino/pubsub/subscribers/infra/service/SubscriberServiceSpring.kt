package com.guilherme.delfino.pubsub.subscribers.infra.service

import com.guilherme.delfino.pubsub.subscribers.application.service.SubscriberService
import com.guilherme.delfino.pubsub.subscribers.domain.entity.Subscriber
import com.guilherme.delfino.pubsub.subscribers.domain.repository.SubscriberRepository
import com.guilherme.delfino.pubsub.subscribers.infra.database.jpa.repository.SubscriberRepositoryImpl
import jakarta.transaction.Transactional
import lombok.extern.slf4j.Slf4j
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class SubscriberServiceSpring : SubscriberService {
    private val repository: SubscriberRepositoryImpl
    constructor(repository: SubscriberRepositoryImpl) {
        this.repository = repository
        this.log = LoggerFactory.getLogger(SubscriberServiceSpring::class.java)
        log.info("{}", repository)
    }

    val log: Logger

    @Transactional
    @CacheEvict("subscribers", allEntries = true)
    override fun create(subscriber: Subscriber) = repository.save(subscriber)
    @Cacheable("subscribers")
    override fun getAll(): List<Subscriber> {
        log.info("repository: {} ", repository);
        return repository.findAll()
    }
    @Cacheable("subscriber", key = "#id", condition = "#result != null")
    override fun getById(id: String) = repository.findById(UUID.fromString(id))
}