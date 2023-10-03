package com.guilherme.delfino.pubsub.subscribers.domain.entity.vo

import com.fasterxml.jackson.annotation.JsonValue
class SubscriberEmail private constructor(private val value: String) {

    companion object{
        private val EMAIL_REGEX = Regex("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")
        fun of(email: String): SubscriberEmail{
            validateEmail(email)
            return SubscriberEmail(value = email)
        }

        private fun validateEmail(email: String) {
            require(EMAIL_REGEX.matches(email)) { "Email does not valid" }
        }
    }
    @JsonValue
    override fun toString(): String {
        return value;
    }
}