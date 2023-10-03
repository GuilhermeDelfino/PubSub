package com.guilherme.delfino.pubsub.events.application.useCase;

import com.guilherme.delfino.pubsub.events.domain.entity.Event;

public interface PublishEventToQueue {
    void publish(Event event);
}
