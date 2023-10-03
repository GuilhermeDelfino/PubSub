package com.guilherme.delfino.pubsub.notification.infra.useCase;

import com.guilherme.delfino.pubsub.notification.application.useCase.GetAllSubscribers;
import com.guilherme.delfino.pubsub.notification.domain.entity.Subscriber;
import com.guilherme.delfino.pubsub.notification.domain.gateway.GatewaySubscribers;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetAllSubscribersMS implements GetAllSubscribers {
    private final GatewaySubscribers gatewaySubscribers;
    @SneakyThrows
    public List<Subscriber> execute(){
        return gatewaySubscribers.findAll();
    }
}
