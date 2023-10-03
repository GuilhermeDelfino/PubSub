package com.guilherme.delfino.pubsub.subscribers.domain.entity.vo

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class SubscriberEmailTest{
    @Test
    @DisplayName("Should be not throw IllegalArgumentException when create Value Object")
    fun createVoCorrectly(){
        val emailValid = "email@example.com";
        assertDoesNotThrow(){SubscriberEmail.of(emailValid)}
        assertEquals(emailValid, SubscriberEmail.of(emailValid).toString())
    }
    @Test
    @DisplayName("Should be throw IllegalArgumentException when create Value Object")
    fun createVoIncorrectly(){
        val emailWithoutAtSign = "emailexample.com";
        val emailWithoutDot = "email@examplecom";
        assertThrows(IllegalArgumentException::class.java){SubscriberEmail.of(emailWithoutAtSign)}
        assertThrows(IllegalArgumentException::class.java){SubscriberEmail.of(emailWithoutDot)}
    }

}