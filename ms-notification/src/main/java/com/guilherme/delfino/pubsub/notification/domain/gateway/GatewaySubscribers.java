package com.guilherme.delfino.pubsub.notification.domain.gateway;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.guilherme.delfino.pubsub.notification.domain.entity.Subscriber;

import java.util.List;

public interface GatewaySubscribers {
    List<Subscriber> findAll() throws JsonProcessingException;
}
