package com.guilherme.delfino.pubsub.notification.application.protocol;

import com.guilherme.delfino.pubsub.notification.infra.gateway.dto.GraphQLResponse;
import com.guilherme.delfino.pubsub.notification.infra.protocol.http.graphql.GraphQLQuery;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface HttpClient {
    <T> ResponseEntity<T> execute(String url, HttpMethod method, Class<T> clazz);
    <T, R> ResponseEntity<T> execute(String url, HttpMethod method, HttpEntity<R> request, Class<T> clazz);
    <T> ResponseEntity<List<T>> executeList(String url, HttpMethod method);
    <T> ResponseEntity<T> graphQLQuery(String url, GraphQLQuery query, Class<T> clazz);
    <T> ResponseEntity<T> graphQLQuery(String url, GraphQLQuery query, Class<T> clazz, String jwtToken);
}
