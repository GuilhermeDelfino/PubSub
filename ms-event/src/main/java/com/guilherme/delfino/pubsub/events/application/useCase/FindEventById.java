package com.guilherme.delfino.pubsub.events.application.useCase;

import com.guilherme.delfino.pubsub.events.domain.entity.Event;
import com.guilherme.delfino.pubsub.events.domain.repository.EventRepository;
import org.springframework.stereotype.Service;

@Service
public class FindEventById {

    private final EventRepository repository;

    public FindEventById(EventRepository repository) {
        this.repository = repository;
    }

    public Event execute(String id) {
        return repository.findById(id);
    }
}
