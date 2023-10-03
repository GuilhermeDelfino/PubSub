package com.guilherme.delfino.pubsub.events.domain.repository;

import com.guilherme.delfino.pubsub.events.domain.entity.Event;

import java.util.List;

public interface EventRepository {
    Event save(Event event);
    List<Event> listAll();
    Event findById(String id);
}
