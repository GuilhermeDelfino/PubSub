package com.guilherme.delfino.pubsub.events.infra.configuration.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EventQueueConfig {

    @Value("${rabbit.queue.event.name}")
    private String eventQueueName;
    @Value("${rabbit.exchange.event.name}")
    private String eventExchangeName;
    @Value("${rabbit.routing.key.event}")
    private String eventRoutingKey;
    @Bean
    public Queue queue(){
        return new Queue(eventQueueName, true, false, false);
    }
    @Bean
    public DirectExchange exchange(){
        return new DirectExchange(eventExchangeName);
    }
    @Bean
    public Binding binding(){
        return new Binding(eventQueueName, Binding.DestinationType.QUEUE, eventExchangeName, eventRoutingKey, null);
    }

}
