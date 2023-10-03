package com.guilherme.delfino.pubsub.notification.infra.rabbitmq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.guilherme.delfino.pubsub.notification.domain.entity.Event;
import com.guilherme.delfino.pubsub.notification.domain.entity.Subscriber;
import com.guilherme.delfino.pubsub.notification.infra.useCase.GetAllSubscribersMS;
import com.guilherme.delfino.pubsub.notification.infra.useCase.SendEmailNewEventCreateBySimpleMailSpring;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
@Log4j2
public class ConsumerEvent {

    private final SendEmailNewEventCreateBySimpleMailSpring useCase;
    private final GetAllSubscribersMS getAllSubscribersMS;
    private final ObjectMapper mapper;
    @RabbitListener(queues = {"${rabbit.queue.event.name}"})
    public void consumeEvent(@Payload String event) throws InterruptedException {
        try {
        log.info("Event delivered {}", event);
        Event evento = mapper.readValue(event, Event.class);
        log.info("event: {}", evento);
        List<Subscriber> subscribers = getAllSubscribersMS.execute();
        log.info("subscribers IDs: {}", subscribers.stream().map(Subscriber::getId).toList());
        subscribers.forEach(sub-> {
            int retries = 0;
            try {
                execute(retries, evento, sub);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        } catch (JsonProcessingException e) {
            Thread.sleep(3000);
            throw new RuntimeException(e);
        }
    }

    private void execute(int retries, Event ev, Subscriber sub) throws InterruptedException {
        try {
            useCase.execute(ev, sub);
        }catch (Exception e){
            Thread.sleep(2000);
            if(retries == 3){
                throw e;
            }
            retries++;
            execute(retries, ev, sub);
        }
    }
}
