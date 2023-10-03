package com.guilherme.delfino.pubsub.subscribers.infra.service

import com.guilherme.delfino.pubsub.subscribers.domain.entity.vo.SubscriberEmail
import com.guilherme.delfino.pubsub.subscribers.domain.entity.vo.SubscriberName
import com.guilherme.delfino.pubsub.subscribers.domain.exceptions.EntityNotFound
import com.guilherme.delfino.pubsub.subscribers.domain.repository.SubscriberRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers

import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension
import java.time.LocalDate
import java.util.*

@ExtendWith(MockitoExtension::class)
class SubscriberServiceSpringTest{
    @Mock
    private lateinit var repository: SubscriberRepository
    @InjectMocks
    private lateinit var service: SubscriberServiceSpring


    @Test
    @DisplayName("Should be create correcly")
    fun create() {
        val subscribeDate = LocalDate.parse("2023-11-11");
        val subscriber = com.guilherme.delfino.pubsub.subscribers.domain.entity.Subscriber(
            name = SubscriberName.of("Example"),
            email = SubscriberEmail.of("example@example.com"),
            subscribeDate = subscribeDate
        )
        `when`(repository.save(subscriber)).thenReturn(subscriber)
        val created = service.create(subscriber)
        assertEquals(subscriber, created)
    }

    @DisplayName("Should be return a subscriber")
    @Test
    fun getByIdCorrecly() {
        val id = UUID.randomUUID();
        val subscriber = com.guilherme.delfino.pubsub.subscribers.domain.entity.Subscriber(
            name = SubscriberName.of("Example"),
            email = SubscriberEmail.of("example@example.com"),
            subscribeDate = LocalDate.parse("2023-11-11"),
            id = id
        )

        `when`(repository.findById(id)).thenReturn(subscriber)

        val byId = service.getById(id.toString());

        assertEquals("Example", byId.name.toString())
        assertEquals(id, byId.id)
    }

    @DisplayName("Should be throw EntityNotFound exception")
    @Test
    fun getByIdThrowsException() {
        val id = UUID.randomUUID();
        `when`(repository.findById(id)).thenThrow(EntityNotFound::class.java)
        assertThrows(EntityNotFound::class.java){service.getById(id.toString())}
    }

    @DisplayName("Should be return a list with 2 subscribers")
    @Test
    fun getAll() {
        `when`(repository.findAll()).thenReturn(listOf(
            com.guilherme.delfino.pubsub.subscribers.domain.entity.Subscriber(
                name = SubscriberName.of("Example"),
                email = SubscriberEmail.of("example@example.com"),
                subscribeDate = LocalDate.parse("2023-11-01")
            ), com.guilherme.delfino.pubsub.subscribers.domain.entity.Subscriber(
                name = SubscriberName.of("Example 2 "),
                email = SubscriberEmail.of("example2@example.com"),
                subscribeDate = LocalDate.parse("2023-12-01")
            )
        ))

        val all = service.getAll()

        assertTrue(all.isNotEmpty())
        assertEquals(2, all.size)
        assertFalse(all[0].id == all[1].id)
    }
}