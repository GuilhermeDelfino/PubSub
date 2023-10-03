package com.guilherme.delfino.pubsub.notification.infra.protocol.http.graphql;

import com.guilherme.delfino.pubsub.notification.infra.gateway.dto.FindAllDataMSSubscribers;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GraphQLResponse<T extends Object> {
    private FindAllDataMSSubscribers data;
}
