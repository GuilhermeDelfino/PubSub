package com.guilherme.delfino.pubsub.subscribers.infra.configuration

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.cache.RedisCacheConfiguration
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair
import java.time.Duration


@Configuration
class RedisConfiguration {
    @Bean
    fun cacheConfiguration(mapper: ObjectMapper): RedisCacheConfiguration? {
        val genericJackson2JsonRedisSerializer = GenericJackson2JsonRedisSerializer(mapper)
        return RedisCacheConfiguration.defaultCacheConfig()
            .entryTtl(Duration.ofMinutes(60))
            .disableCachingNullValues()
            .serializeValuesWith(SerializationPair.fromSerializer(genericJackson2JsonRedisSerializer))
    }
}