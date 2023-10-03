package com.guilherme.delfino.pubsub.notification.infra.gateway.dto;

import lombok.Data;
import lombok.ToString;

import java.util.LinkedHashMap;

@Data
@ToString
public class GraphQLResponse<T> {
    LinkedHashMap<String, T> data;
}
