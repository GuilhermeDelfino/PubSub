package com.guilherme.delfino.pubsub.notification.mock;

import com.guilherme.delfino.pubsub.notification.domain.entity.Subscriber;

import java.util.List;
import java.util.UUID;

public class SubscribersMock {
    public static List<Subscriber> getFakeList(){
        return List.of(
                new Subscriber(UUID.randomUUID(),"Name example", "example@example.com"),
                new Subscriber(UUID.randomUUID(),"example", "anyone@example.com")
        );
    }
}
