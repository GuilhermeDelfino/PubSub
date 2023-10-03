package com.guilherme.delfino.pubsub.events.infra.useCase;

import com.guilherme.delfino.pubsub.events.domain.entity.Event;
import com.guilherme.delfino.pubsub.events.application.useCase.PublishEventToQueue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PublishEventToRabbitQueue implements PublishEventToQueue {
    @Value("${rabbit.exchange.event.name}")
    private String eventExchangeName;
    @Value("${rabbit.routing.key.event}")
    private String eventRoutingKey;
    private final RabbitTemplate rabbitTemplate;
    public PublishEventToRabbitQueue(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
    @Override
    public void publish(Event event) {
        rabbitTemplate.convertAndSend(eventExchangeName, eventRoutingKey, event);
    }
}
