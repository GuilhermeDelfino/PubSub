package com.guilherme.delfino.pubsub.subscribers.domain.entity.vo

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class SubscriberNameTest{
    @Test
    @DisplayName("Should be not throw IllegalArgumentException when create Value Object")
    fun createVoCorrectly(){
        val validName = "Example";
        assertDoesNotThrow(){SubscriberName.of(validName)}
        assertEquals(validName, SubscriberName.of(validName).toString())
    }
    @Test
    @DisplayName("Should be throw IllegalArgumentException when create Value Object")
    fun createVoIncorrectly(){
        val shortName = "12";
        val longName = "1".repeat(200);
        assertThrows(IllegalArgumentException::class.java){SubscriberName.of(shortName)}
        assertThrows(IllegalArgumentException::class.java){SubscriberName.of(longName)}
    }
}