package com.guilherme.delfino.pubsub.events.application.useCase;

import com.guilherme.delfino.pubsub.events.domain.entity.Event;
import com.guilherme.delfino.pubsub.events.domain.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListAllEvents {

    private final EventRepository repository;

    public ListAllEvents(EventRepository repository) {
        this.repository = repository;
    }
    public List<Event> execute() {
        return repository.listAll();
    }
}
