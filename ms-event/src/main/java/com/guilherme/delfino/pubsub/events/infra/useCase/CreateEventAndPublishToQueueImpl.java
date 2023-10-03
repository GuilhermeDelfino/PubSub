package com.guilherme.delfino.pubsub.events.infra.useCase;

import com.guilherme.delfino.pubsub.events.domain.entity.Event;
import com.guilherme.delfino.pubsub.events.domain.repository.EventRepository;
import com.guilherme.delfino.pubsub.events.application.useCase.CreateEvent;
import org.springframework.stereotype.Service;

@Service
public class CreateEventAndPublishToQueueImpl implements CreateEvent {

    private final EventRepository repository;

    public CreateEventAndPublishToQueueImpl(EventRepository repository) {
        this.repository = repository;
    }

    @Override
    public Event create(Event event) {
        return repository.save(event);
    }
}
