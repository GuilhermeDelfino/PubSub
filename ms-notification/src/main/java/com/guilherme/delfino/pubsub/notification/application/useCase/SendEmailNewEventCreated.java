package com.guilherme.delfino.pubsub.notification.application.useCase;

import com.guilherme.delfino.pubsub.notification.domain.entity.Event;
import com.guilherme.delfino.pubsub.notification.domain.entity.Subscriber;

public interface SendEmailNewEventCreated {
    void execute(Event event, Subscriber subscriber);
}
