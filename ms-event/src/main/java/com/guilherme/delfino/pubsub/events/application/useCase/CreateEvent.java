package com.guilherme.delfino.pubsub.events.application.useCase;

import com.guilherme.delfino.pubsub.events.domain.entity.Event;

public interface CreateEvent {
    Event create(Event event);
}
