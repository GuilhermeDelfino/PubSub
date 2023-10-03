package com.guilherme.delfino.pubsub.subscribers.infra.database.jpa.repository

import com.guilherme.delfino.pubsub.subscribers.domain.entity.vo.SubscriberEmail
import com.guilherme.delfino.pubsub.subscribers.domain.entity.vo.SubscriberName
import com.guilherme.delfino.pubsub.subscribers.domain.exceptions.EntityNotFound
import com.guilherme.delfino.pubsub.subscribers.infra.database.jpa.mapper.SubscriberJpaMapper
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.mockito.Mockito.*
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import java.time.LocalDate
import java.util.*

@ExtendWith(MockitoExtension::class)
class SubscriberRepositoryImplTest {

    @InjectMocks
    private lateinit var repoSubscriber: SubscriberRepositoryImpl;
    @Mock
    private lateinit var  jpaRepo: JpaSubscriberRepository;

    @Test
    @DisplayName("Should be save a new subscriber")
    fun save() {
        val sub = com.guilherme.delfino.pubsub.subscribers.domain.entity.Subscriber(
            name = SubscriberName.of("Example"),
            email = SubscriberEmail.of("example@example.com"),
            subscribeDate = LocalDate.parse("2023-11-01")
        )

        `when`(jpaRepo.save(any())).thenReturn(SubscriberJpaMapper.entityToModel(sub))
        val saved = repoSubscriber.save(sub);
        assertEquals(sub, saved)
    }

    @Test
    @DisplayName("Should be return 2 subscribers")
    fun findAll() {
        val map = listOf(
            com.guilherme.delfino.pubsub.subscribers.domain.entity.Subscriber(
                name = SubscriberName.of("Example"),
                email = SubscriberEmail.of("example@example.com"),
                subscribeDate = LocalDate.parse("2023-11-01")
            ), com.guilherme.delfino.pubsub.subscribers.domain.entity.Subscriber(
                name = SubscriberName.of("Example"),
                email = SubscriberEmail.of("example@example.com"),
                subscribeDate = LocalDate.parse("2023-11-01")
            )
        ).map(SubscriberJpaMapper::entityToModel)

        `when`(jpaRepo.findAll()).thenReturn(map)

        val findAll = repoSubscriber.findAll()

        assertTrue(findAll.isNotEmpty())
        assertEquals(2, findAll.size)
        assertEquals(map[0].id, findAll[0].id)
    }

    @Test
    @DisplayName("Should be return a subscriber correctly")
    fun findById() {

        val sub = com.guilherme.delfino.pubsub.subscribers.domain.entity.Subscriber(
            name = SubscriberName.of("Example"),
            email = SubscriberEmail.of("example@example.com"),
            subscribeDate = LocalDate.parse("2023-11-01")
        )

        `when`(jpaRepo.findById(any())).thenReturn(Optional.of(SubscriberJpaMapper.entityToModel(sub)))
        val findById = repoSubscriber.findById(UUID.randomUUID())

        assertEquals(sub, findById)
    }
    @Test
    @DisplayName("Should be throw when subscriber is not present")
    fun findByIdIsNotPresent() {
        `when`(jpaRepo.findById(any())).thenReturn(Optional.empty())
        assertThrows(EntityNotFound::class.java){ repoSubscriber.findById(UUID.randomUUID())}
    }
}