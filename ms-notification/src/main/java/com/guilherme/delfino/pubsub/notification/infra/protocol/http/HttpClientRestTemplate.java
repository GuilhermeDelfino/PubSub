package com.guilherme.delfino.pubsub.notification.infra.protocol.http;

import com.guilherme.delfino.pubsub.notification.application.protocol.HttpClient;
import com.guilherme.delfino.pubsub.notification.infra.gateway.dto.GraphQLResponse;
import com.guilherme.delfino.pubsub.notification.infra.protocol.http.graphql.GraphQLQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@Component
public class HttpClientRestTemplate implements HttpClient {
    private static final Logger logger = LoggerFactory.getLogger(HttpClientRestTemplate.class);

    private final RestTemplate client;

    public HttpClientRestTemplate(RestTemplate client) {
        this.client = client;
    }

    @Override
    public <T> ResponseEntity<T> execute(String url, HttpMethod method, Class<T> clazz) {
        ResponseEntity<T> response = client
                .exchange(
                        url,
                        method,
                        null,
                        clazz
                );

        if(!response.getStatusCode().is2xxSuccessful()){
            logger.error(Objects.requireNonNull(response.getBody()).toString());
        }
        return response;
    }

    @Override
    public <T, R> ResponseEntity<T> execute(String url, HttpMethod method, HttpEntity<R> request, Class<T> clazz) {
        ResponseEntity<T> response = client
                .exchange(
                        url,
                        method,
                        request,
                        clazz
                );

        if(!response.getStatusCode().is2xxSuccessful()){
            logger.error(Objects.requireNonNull(response.getBody()).toString());
        }
        return response;
    }

    @Override
    public <T> ResponseEntity<List<T>> executeList(String url, HttpMethod method) {
        ResponseEntity<List<T>> response = client
                .exchange(
                        url,
                        method,
                        null,
                        new ParameterizedTypeReference<>() {
                        }
                );

        if(!response.getStatusCode().is2xxSuccessful()){
            logger.error(Objects.requireNonNull(response.getBody()).toString());
        }
        return response;
    }

    @Override
    public <T> ResponseEntity<T> graphQLQuery(String url, GraphQLQuery query, Class<T> clazz){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<GraphQLQuery> request = new HttpEntity<>(query, headers);
 
        ResponseEntity<T> response = client
                .exchange(
                        url,
                        HttpMethod.POST,
                        request,
                        clazz
                );

        if(!response.getStatusCode().is2xxSuccessful()){
            logger.error(Objects.requireNonNull(response.getBody()).toString());
        }

        return response;
    }
    @Override
    public <T> ResponseEntity<T> graphQLQuery(String url, GraphQLQuery query, Class<T> clazz, String jwtToken){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(jwtToken);
        HttpEntity<GraphQLQuery> request = new HttpEntity<>(query, headers);

        ResponseEntity<T> response = client
                .exchange(
                        url,
                        HttpMethod.POST,
                        request,
                        clazz
                );

        if(!response.getStatusCode().is2xxSuccessful()){
            logger.error(Objects.requireNonNull(response.getBody()).toString());
        }

        return response;
    }
}
