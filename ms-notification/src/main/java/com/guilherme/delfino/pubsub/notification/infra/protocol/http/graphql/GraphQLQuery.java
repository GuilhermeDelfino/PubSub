package com.guilherme.delfino.pubsub.notification.infra.protocol.http.graphql;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class GraphQLQuery {
    private String query;
    private Map<String, Object> variables;

    public GraphQLQuery(String query) {
        this.query = query;
    }

    public GraphQLQuery(String query, Map<String, Object> variables) {
        this.query = query;
        this.variables = variables;
    }
}
