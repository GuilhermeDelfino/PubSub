package com.guilherme.delfino.pubsub.notification.infra.gateway.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.guilherme.delfino.pubsub.notification.domain.entity.Subscriber;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FindAllDataMSSubscribers {
    private List<Subscriber> findAll;
}
