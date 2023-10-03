package com.guilherme.delfino.pubsub.notification.infra.gateway;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.guilherme.delfino.pubsub.notification.application.protocol.HttpClient;
import com.guilherme.delfino.pubsub.notification.domain.entity.Subscriber;
import com.guilherme.delfino.pubsub.notification.domain.gateway.GatewaySubscribers;
import com.guilherme.delfino.pubsub.notification.domain.service.AuthService;
import com.guilherme.delfino.pubsub.notification.infra.gateway.dto.FindAllDataMSSubscribers;
import com.guilherme.delfino.pubsub.notification.infra.gateway.dto.GraphQLResponse;
import com.guilherme.delfino.pubsub.notification.infra.gateway.dto.ResponseFindAllMSSubscribers;
import com.guilherme.delfino.pubsub.notification.infra.protocol.http.graphql.GraphQLQuery;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Objects;

@Service
@Log4j2
public class GatewaySubscribersMS implements GatewaySubscribers {
    @Value("${ms.url.subscribers}")
    private String BASE_URL_MS_SUBSCRIBERS;
    private final HttpClient client;
    private final ObjectMapper mapper;
    private final AuthService authService;

    public GatewaySubscribersMS(HttpClient client, ObjectMapper mapper, AuthService authService) {
        this.client = client;
        this.mapper = mapper;
        this.authService = authService;
    }

    public List<Subscriber> findAll() {
        String url = BASE_URL_MS_SUBSCRIBERS;

        GraphQLQuery query = new GraphQLQuery("""
                query {
                    findAll {
                        id
                        email
                        name
                    }
                }
                """);

        String jwtToken = authService.getToken();
        ResponseEntity<ResponseFindAllMSSubscribers> response = client.graphQLQuery(url, query, ResponseFindAllMSSubscribers.class, jwtToken);
        if(response.getStatusCode().is2xxSuccessful()){
            return Objects.requireNonNull(response.getBody()).getData().getFindAll();
        }

        throw new RuntimeException("Ocorred an error to findAll subscribers");
    }
}
