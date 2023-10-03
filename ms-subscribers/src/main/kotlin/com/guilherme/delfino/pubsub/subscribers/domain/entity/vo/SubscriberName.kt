package com.guilherme.delfino.pubsub.subscribers.domain.entity.vo;

import com.fasterxml.jackson.annotation.JsonValue

class SubscriberName private constructor( private val value: String) {

    companion object{
        fun of(name: String): SubscriberName{
            validateName(name)
            return SubscriberName(name)
        }
        private fun validateName(name: String){
            require(name.isNotEmpty() && name.length > 2 && name.length < 150) { "Subscriber name must have between 3 and 150 characters" }
        }
    }
    @JsonValue
    override fun toString(): String {
        return value;
    }


}
