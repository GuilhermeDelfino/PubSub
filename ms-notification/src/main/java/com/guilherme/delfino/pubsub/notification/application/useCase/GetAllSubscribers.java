package com.guilherme.delfino.pubsub.notification.application.useCase;

import com.guilherme.delfino.pubsub.notification.domain.entity.Subscriber;

import java.util.List;

public interface GetAllSubscribers {
    List<Subscriber> execute();
}
